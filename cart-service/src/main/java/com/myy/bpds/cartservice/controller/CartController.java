package com.myy.bpds.cartservice.controller;

import com.myy.bpds.cartservice.dto.AddCartItemDTO;
import com.myy.bpds.cartservice.dto.CartDTO;
import com.myy.bpds.cartservice.service.CartItemService;
import com.myy.bpds.cartservice.service.CartService;
import com.myy.bpds.common.dto.Result;
import lombok.RequiredArgsConstructor;
import org.apache.seata.rm.datasource.DataSourceProxy;
import org.apache.seata.rm.datasource.xa.DataSourceProxyXA;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车Controller
 */

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    private final CartItemService cartItemService;

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public Result<Void> addToCart(
            @RequestParam String itemId,
            @RequestParam(defaultValue = "1") Integer quantity) {
        cartItemService.addToCart(itemId, quantity);
        return Result.ok();
    }

    /**
     * 批量添加商品到购物车
     *
     * @param addCartItems
     * @return
     */
    @PostMapping("/batchAdd")
    public Result<Void> batchAddToCart(@RequestBody List<AddCartItemDTO> addCartItems) {
        cartItemService.batchAddToCart(addCartItems);
        return Result.ok();
    }

    /**
     * 查询用户完整购物车信息（包含购物车和购物车项列表）
     */
    @GetMapping("/info")
    public Result<CartDTO> getCartInfo() {
        DataSourceProxy at;
        DataSourceProxyXA XA;
        return Result.ok(cartService.queryUserCartDetails());
    }

    /**
     * 清空购物车
     */
    @PostMapping("/clear")
    public Result<Void> clearCart() {
        cartService.clearCart();
        return Result.ok();
    }
}
