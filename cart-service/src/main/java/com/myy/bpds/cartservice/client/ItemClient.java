package com.myy.bpds.cartservice.client;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.itemservice.entity.ItemEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "item-service")
public interface ItemClient {

    @GetMapping("/item-service/api/item")
    Result<List<ItemEntity>> getItem(@RequestParam List<String> ids);
}