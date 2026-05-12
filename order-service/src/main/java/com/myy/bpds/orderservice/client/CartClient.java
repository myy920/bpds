package com.myy.bpds.orderservice.client;

import com.myy.bpds.cartservice.dto.CartDTO;
import com.myy.bpds.common.dto.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 购物车服务Feign客户端
 */
@FeignClient(value = "cart-service", fallbackFactory = CartClient.CartClientFallbackFactory.class)
public interface CartClient {

    /**
     * 查询用户购物车信息
     */
    @GetMapping("/cart-service/cart/info")
    Result<CartDTO> getCartInfo();

    /**
     * 清空购物车
     */
    @PostMapping("/cart-service/cart/clear")
    Result<Void> clearCart();

    @Component
    class CartClientFallbackFactory implements FallbackFactory<CartClient> {
        @Override
        public CartClient create(Throwable cause) {
            return new CartClient() {
                @Override
                public Result<CartDTO> getCartInfo() {
                    return Result.error("购物车服务调用失败: " + cause.getMessage());
                }

                @Override
                public Result<Void> clearCart() {
                    return Result.error("购物车服务调用失败: " + cause.getMessage());
                }
            };
        }
    }
}
