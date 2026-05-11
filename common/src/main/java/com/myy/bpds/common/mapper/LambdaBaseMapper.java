package com.myy.bpds.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface LambdaBaseMapper<T> extends MybatisPlus3IServiceAdapter<T> {
    // 日志
    Logger log = LoggerFactory.getLogger(LambdaBaseMapper.class);

    /**
     * 查询单条记录（BaseMapper 原始方法名）
     */
    default T selectOne(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return getOne(wrapperSetting, false);
    }

    /**
     * 查询单条记录（BaseMapper 原始方法名，带异常控制）
     */
    default T selectOne(Consumer<LambdaQueryWrapper<T>> wrapperSetting, boolean throwEx) {
        return getOne(wrapperSetting, throwEx);
    }

    /**
     * 查询单条记录
     */
    default T getOne(Consumer<LambdaQueryWrapper<T>> wrapperSetting, boolean throwEx) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return (T) this.getBaseMapper().selectOne(wrapper, throwEx);
    }

    /**
     * 查询单条记录（不抛异常）
     */
    default T getOne(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return getOne(wrapperSetting, false);
    }

    /**
     * 查询单条记录并包装为 Optional
     */
    default java.util.Optional<T> getOneOpt(Consumer<LambdaQueryWrapper<T>> wrapperSetting, boolean throwEx) {
        return java.util.Optional.ofNullable(getOne(wrapperSetting, throwEx));
    }

    /**
     * 查询单条记录并包装为 Optional（不抛异常）
     */
    default java.util.Optional<T> getOneOpt(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return getOneOpt(wrapperSetting, false);
    }

    /**
     * 查询 Map 数据
     */
    default Map<String, Object> getMap(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectMaps(wrapper).stream().findFirst().orElse(null);
    }

    /**
     * 查询单个对象值
     */
    default <V> V getObj(Consumer<LambdaQueryWrapper<T>> wrapperSetting, Function<? super Object, V> mapper) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        List<Object> objs = this.getBaseMapper().selectObjs(wrapper);
        return SqlHelper.getObject((org.apache.ibatis.logging.Log) log,
                objs.stream().map(mapper).collect(Collectors.toList()));
    }

    /**
     * 删除记录（BaseMapper 原始方法名）
     */
    default int delete(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().delete(wrapper);
    }

    /**
     * 删除记录
     */
    default boolean remove(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return SqlHelper.retBool(this.getBaseMapper().delete(wrapper));
    }

    /**
     * 更新记录（使用 LambdaUpdateWrapper）
     */
    default int update(Consumer<LambdaUpdateWrapper<T>> wrapperSetting) {
        LambdaUpdateWrapper<T> wrapper = Wrappers.lambdaUpdate();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().update(null, wrapper);
    }

    /**
     * 更新记录（实体 + LambdaUpdateWrapper）
     */
    default int update(T entity, Consumer<LambdaUpdateWrapper<T>> wrapperSetting) {
        LambdaUpdateWrapper<T> wrapper = Wrappers.lambdaUpdate();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().update(entity, wrapper);
    }

    /**
     * 更新记录并返回 boolean
     */
    default boolean tryUpdate(Consumer<LambdaUpdateWrapper<T>> wrapperSetting) {
        return SqlHelper.retBool(update(wrapperSetting));
    }

    /**
     * 更新记录并返回 boolean（实体 + LambdaUpdateWrapper）
     */
    default boolean tryUpdate(T entity, Consumer<LambdaUpdateWrapper<T>> wrapperSetting) {
        return SqlHelper.retBool(update(entity, wrapperSetting));
    }

    /**
     * 判断是否存在
     */
    default boolean exists(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().exists(wrapper);
    }

    /**
     * 统计数量（BaseMapper 原始方法名）
     */
    default Long selectCount(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectCount(wrapper);
    }

    /**
     * 统计数量
     */
    default long count(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return SqlHelper.retCount(this.getBaseMapper().selectCount(wrapper));
    }

    /**
     * 查询列表（BaseMapper 原始方法名）
     */
    default List<T> selectList(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return list(wrapperSetting);
    }

    /**
     * 查询列表
     */
    default List<T> list(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectList(wrapper);
    }

    /**
     * 分页查询列表（BaseMapper 原始方法名）
     */
    default List<T> selectList(IPage<T> page, Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return list(page, wrapperSetting);
    }

    /**
     * 分页查询列表
     */
    default List<T> list(IPage<T> page, Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectList(page, wrapper);
    }

    /**
     * 分页查询（BaseMapper 原始方法名）
     */
    default <E extends IPage<T>> E selectPage(E page, Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return page(page, wrapperSetting);
    }

    /**
     * 分页查询
     */
    default <E extends IPage<T>> E page(E page, Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return (E) this.getBaseMapper().selectPage(page, wrapper);
    }

    /**
     * 查询 Map 列表（BaseMapper 原始方法名）
     */
    default List<Map<String, Object>> selectMaps(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return listMaps(wrapperSetting);
    }

    /**
     * 查询 Map 列表
     */
    default List<Map<String, Object>> listMaps(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectMaps(wrapper);
    }

    /**
     * 分页查询 Map 列表（BaseMapper 原始方法名）
     */
    default <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return pageMaps(page, wrapperSetting);
    }

    /**
     * 分页查询 Map 列表
     */
    default List<Map<String, Object>> listMaps(IPage<? extends Map<String, Object>> page,
                                               Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectMaps(page, wrapper);
    }

    /**
     * 查询对象列表（BaseMapper 原始方法名）
     */
    default <E> List<E> selectObjs(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        return listObjs(wrapperSetting);
    }

    /**
     * 查询对象列表
     */
    default <E> List<E> listObjs(Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectObjs(wrapper);
    }

    /**
     * 查询对象列表并转换
     */
    default <V> List<V> listObjs(Consumer<LambdaQueryWrapper<T>> wrapperSetting, Function<? super Object, V> mapper) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return this.getBaseMapper().selectObjs(wrapper).stream()
                .filter(Objects::nonNull)
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * 分页查询 Map
     */
    default <E extends IPage<Map<String, Object>>> E pageMaps(E page, Consumer<LambdaQueryWrapper<T>> wrapperSetting) {
        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapperSetting.accept(wrapper);
        return (E) this.getBaseMapper().selectMapsPage(page, wrapper);
    }
}
