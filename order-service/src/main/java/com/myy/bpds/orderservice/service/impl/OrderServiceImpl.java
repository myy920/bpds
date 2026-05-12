package com.myy.bpds.orderservice.service.impl;

import com.myy.bpds.cartservice.dto.CartDTO;
import com.myy.bpds.cartservice.dto.CartItemDTO;
import com.myy.bpds.common.exception.BpdsException;
import com.myy.bpds.common.utils.BpdsContextHolder;
import com.myy.bpds.itemservice.dto.StockDeductionRequest;
import com.myy.bpds.orderservice.client.CartClient;
import com.myy.bpds.orderservice.client.ItemClient;
import com.myy.bpds.orderservice.constants.OrderErrorCode;
import com.myy.bpds.orderservice.dao.OrderDao;
import com.myy.bpds.orderservice.dao.OrderItemDao;
import com.myy.bpds.orderservice.dto.CreateOrderRequest;
import com.myy.bpds.orderservice.dto.OrderResponse;
import com.myy.bpds.orderservice.entity.OrderEntity;
import com.myy.bpds.orderservice.entity.OrderItemEntity;
import com.myy.bpds.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单Service实现类
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final CartClient cartClient;
    private final ItemClient itemClient;

    @Override
    // @GlobalTransactional(name = "create-order-tx")
    public OrderResponse createOrder(CreateOrderRequest request) {
        // 查询购物车信息
        CartDTO cartDTO = cartClient.getCartInfo().getData();
        if (cartDTO == null) {
            throw new BpdsException(OrderErrorCode.CART_EMPTY);
        }
        List<CartItemDTO> cartItems = ListUtils.emptyIfNull(cartDTO.getItems()).stream()
                .filter(a -> ObjectUtils.allNotNull(a.getItem(), a.getCartItem()))
                .toList();
        if (CollectionUtils.isEmpty(cartItems)) {
            throw new BpdsException(OrderErrorCode.CART_EMPTY);
        }
        // 创建订单
        OrderEntity order = createOrder(request, cartItems);

        // 扣减库存
        deductStock(cartItems);

        // 清空购物车
        cartClient.clearCart();

        // 返回订单信息
        OrderResponse response = new OrderResponse();
        response.setOrder(order);
        return response;
    }

    private OrderEntity createOrder(CreateOrderRequest request, List<CartItemDTO> cartItems) {
        // 计算订单总金额
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItemDTO cartItem : cartItems) {
            BigDecimal itemTotal = cartItem.getItem().getPrice()
                    .multiply(new BigDecimal(cartItem.getCartItem().getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }
        // 创建订单
        OrderEntity order = new OrderEntity();
        order.setUserId(BpdsContextHolder.currentUserId());
        order.setOrderNo(generateOrderNo());
        order.setStatus(0); // 待支付
        order.setRemark(request.getRemark());
        order.setTotalAmount(totalAmount);
        orderDao.save(order);
        // 创建订单明细
        List<OrderItemEntity> orderItems = new ArrayList<>();
        for (CartItemDTO cartItem : cartItems) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrderId(order.getId());
            orderItem.setItemId(cartItem.getItem().getId());
            orderItem.setItemName(cartItem.getItem().getName());
            orderItem.setItemPrice(cartItem.getItem().getPrice());
            orderItem.setQuantity(cartItem.getCartItem().getQuantity());
            orderItem.setSubtotal(cartItem.getItem().getPrice()
                    .multiply(new BigDecimal(cartItem.getCartItem().getQuantity())));
            orderItems.add(orderItem);

        }

        orderItemDao.saveBatch(orderItems);
        return order;
    }

    private void deductStock(List<CartItemDTO> cartItems) {
        // 准备批量扣减库存请求
        List<StockDeductionRequest> stockRequests = new ArrayList<>();
        for (CartItemDTO cartItem : cartItems) {
            StockDeductionRequest stockRequest = new StockDeductionRequest();
            stockRequest.setItemId(cartItem.getItem().getId());
            stockRequest.setQuantity(cartItem.getCartItem().getQuantity());
            stockRequests.add(stockRequest);
        }
        // 批量扣减库存
        if (CollectionUtils.isNotEmpty(stockRequests)) {
            itemClient.batchDeductStock(stockRequests);
        }

    }

    /**
     * 生成订单号 格式：ORD + yyyyMMddHHmmss + 6位随机数
     */
    private String generateOrderNo() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int random = (int) (Math.random() * 900000) + 100000;
        return "ORD" + timestamp + random;
    }
}
