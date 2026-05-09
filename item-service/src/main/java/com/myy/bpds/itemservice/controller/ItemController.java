package com.myy.bpds.itemservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myy.bpds.common.dto.Result;
import com.myy.bpds.itemservice.entity.ItemEntity;
import com.myy.bpds.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 新增商品
     */
    @PostMapping
    public Result<Void> addItem(@RequestBody ItemEntity item) {
        itemService.save(item);
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
     * 分页查询商品列表
     */
    @GetMapping("/page")
    public Result<Page<ItemEntity>> pageItems(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer status) {
        Page<ItemEntity> page = new Page<>(current, size);
        LambdaQueryWrapper<ItemEntity> wrapper = new LambdaQueryWrapper<>();
        
        if (category != null && !category.isEmpty()) {
            wrapper.eq(ItemEntity::getCategory, category);
        }
        if (status != null) {
            wrapper.eq(ItemEntity::getStatus, status);
        }
        wrapper.orderByDesc(ItemEntity::getCreateTime);
        
        Page<ItemEntity> result = itemService.page(page, wrapper);
        return Result.ok(result);
    }

    /**
     * 查询所有上架商品
     */
    @GetMapping("/list")
    public Result<List<ItemEntity>> listItems() {
        LambdaQueryWrapper<ItemEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ItemEntity::getStatus, 1);
        wrapper.orderByDesc(ItemEntity::getCreateTime);
        List<ItemEntity> items = itemService.list(wrapper);
        return Result.ok(items);
    }
}
