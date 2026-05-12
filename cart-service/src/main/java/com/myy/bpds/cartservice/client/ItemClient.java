package com.myy.bpds.cartservice.client;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.itemservice.entity.ItemEntity;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "item-service", fallbackFactory = ItemClient.ItemClientFallbackFactory.class)
public interface ItemClient {

    @GetMapping("/item-service/item")
    Result<List<ItemEntity>> getItem(@RequestParam List<String> ids);

    @Component
    class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
        @Override
        public ItemClient create(Throwable cause) {
            return new ItemClient() {
                @Override
                public Result<List<ItemEntity>> getItem(List<String> ids) {
                    return Result.ok(List.of());
                }
            };
        }
    }
}