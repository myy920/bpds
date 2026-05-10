package com.myy.bpds.itemservice.controller;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.itemservice.entity.ItemEntity;
import com.myy.bpds.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class MMItemController {
    private final ItemService itemService;

    // 查询商品信息
    @GetMapping("")
    public Result<List<ItemEntity>> getItem(@RequestParam List<String> ids) {
        return Result.ok(itemService.listByIds(ids));
    }
}
