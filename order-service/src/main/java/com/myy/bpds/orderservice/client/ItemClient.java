package com.myy.bpds.orderservice.client;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.itemservice.dto.StockDeductionRequest;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品服务Feign客户端
 */
@FeignClient(value = "item-service", fallbackFactory = ItemClient.ItemClientFallbackFactory.class)
public interface ItemClient {

    /**
     * 扣减库存
     */
    @PostMapping("/item-service/item/deduct-stock")
    Result<Void> deductStock(@RequestParam String itemId, @RequestParam Integer quantity);

    /**
     * 批量扣减库存
     */
    @PostMapping("/item-service/item/batch-deduct-stock")
    Result<Void> batchDeductStock(@RequestBody List<StockDeductionRequest> requests);

    @Component
    class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
        @Override
        public ItemClient create(Throwable cause) {
            return new ItemClient() {
                @Override
                public Result<Void> deductStock(String itemId, Integer quantity) {
                    return Result.error("商品服务调用失败: " + cause.getMessage());
                }

                @Override
                public Result<Void> batchDeductStock(List<StockDeductionRequest> requests) {
                    return Result.error("商品服务批量扣减库存调用失败: " + cause.getMessage());
                }
            };
        }
    }
}
