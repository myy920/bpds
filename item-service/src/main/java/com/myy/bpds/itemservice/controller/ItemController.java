package com.myy.bpds.itemservice.controller;

import com.myy.bpds.common.dto.Result;
import com.myy.bpds.itemservice.entity.ItemEntity;
import com.myy.bpds.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 新增商品
     */
    @PostMapping
    public Result<Void> addItem(@RequestBody ItemEntity item) {
        itemService.save(item);
        return Result.ok();
    }

    /**
     * 批量新增商品
     */
    @PostMapping("/batch")
    public Result<Void> addItemsBatch(@RequestBody List<ItemEntity> items) {
        itemService.saveBatch(items);
        return Result.ok();
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteItem(@PathVariable String id) {
        itemService.removeById(id);
        return Result.ok();
    }

    /**
     * 更新商品
     */
    @PutMapping
    public Result<Void> updateItem(@RequestBody ItemEntity item) {
        itemService.updateById(item);
        return Result.ok();
    }

    /**
     * 查询商品详情
     */
    @GetMapping("/{id}")
    public Result<ItemEntity> getItem(@PathVariable String id) {
        ItemEntity item = itemService.getById(id);
        return Result.ok(item);
    }

    /**
     * 查询所有上架商品
     */
    @GetMapping("/list")
    public Result<List<ItemEntity>> listItems() {
        List<ItemEntity> items = itemService.listActiveItems();
        return Result.ok(items);
    }
}
