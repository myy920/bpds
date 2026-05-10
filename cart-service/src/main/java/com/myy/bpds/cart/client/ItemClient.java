package com.myy.bpds.cart.client;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.itemservice.entity.ItemEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bpds-item")
public interface ItemClient {

    @GetMapping("/bpds-item/api/item")
    Result<List<ItemEntity>> getItem(@RequestParam List<String> ids);
}