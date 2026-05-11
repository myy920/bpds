package com.myy.bpds.common.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.logging.Log;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface MybatisPlus3IServiceAdapter<T> extends BaseMapper<T> {
    // 日志
    Logger log = LoggerFactory.getLogger(MybatisPlus3IServiceAdapter.class);

    default boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, 1000);
    }

    default boolean saveBatch(Collection<T> entityList, int batchSize) {
        return Db.saveBatch(entityList, batchSize);
    }

    default boolean saveOrUpdateBatch(Collection<T> entityList) {
        return saveOrUpdateBatch(entityList, 1000);
    }

    default boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return Db.saveOrUpdateBatch(entityList, batchSize);
    }

    default boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return Db.updateBatchById(entityList, batchSize);
    }

    default boolean saveOrUpdate(T entity) {
        return Db.saveOrUpdate(entity);
    }

    default T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return (T) this.getBaseMapper().selectOne(queryWrapper, throwEx);
    }

    default Optional<T> getOneOpt(Wrapper<T> queryWrapper, boolean throwEx) {
        return Optional.ofNullable(getOne(queryWrapper, throwEx));
    }

    default Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return Db.getMap(queryWrapper);
    }

    default <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return (V) SqlHelper.getObject((Log) log, this.listObjs(queryWrapper, mapper));
    }

    default boolean removeBatchByIds(Collection<?> list) {
        return !CollectionUtils.isEmpty(list) && SqlHelper.retBool(this.getBaseMapper().deleteByIds(list));
    }

    default boolean updateBatchById(Collection<T> entityList) {
        return updateBatchById(entityList, 1000);
    }


    default boolean save(T entity) {
        return SqlHelper.retBool(this.getBaseMapper().insert(entity));
    }


    default boolean removeById(Serializable id) {
        return SqlHelper.retBool(this.getBaseMapper().deleteById(id));
    }


    default boolean removeById(T entity) {
        return SqlHelper.retBool(this.getBaseMapper().deleteById(entity));
    }


    default boolean removeByMap(Map<String, Object> columnMap) {
        Assert.notEmpty(columnMap, "error: columnMap must not be empty", new Object[0]);
        return SqlHelper.retBool(this.getBaseMapper().deleteByMap(columnMap));
    }


    default boolean remove(Wrapper<T> queryWrapper) {
        return SqlHelper.retBool(this.getBaseMapper().delete(queryWrapper));
    }


    default boolean removeByIds(Collection<?> list) {
        return !CollectionUtils.isEmpty(list) && SqlHelper.retBool(this.getBaseMapper().deleteByIds(list));
    }


    default boolean removeByIds(Collection<?> list, boolean useFill) {
        return !CollectionUtils.isEmpty(list) && SqlHelper.retBool(this.getBaseMapper().deleteByIds(list, useFill));
    }


    default boolean tryUpdate(T entity, Wrapper<T> updateWrapper) {
        return SqlHelper.retBool(this.getBaseMapper().update(entity, updateWrapper));
    }

    default boolean tryUpdateById(T entity) {
        return SqlHelper.retBool(this.getBaseMapper().updateById(entity));
    }


    default boolean tryUpdate(Wrapper<T> updateWrapper) {
        return SqlHelper.retBool(update(null, updateWrapper));
    }


    default T getById(Serializable id) {
        return (T) this.getBaseMapper().selectById(id);
    }


    default Optional<T> getOptById(Serializable id) {
        return Optional.ofNullable(this.getBaseMapper().selectById(id));
    }


    default List<T> listByIds(Collection<? extends Serializable> idList) {
        return this.getBaseMapper().selectByIds(idList);
    }


    default List<T> listByMap(Map<String, Object> columnMap) {
        return this.getBaseMapper().selectByMap(columnMap);
    }


    default T getOne(Wrapper<T> queryWrapper) {
        return (T) this.getOne(queryWrapper, true);
    }


    default Optional<T> getOneOpt(Wrapper<T> queryWrapper) {
        return this.getOneOpt(queryWrapper, true);
    }

    default long count() {
        return this.count(Wrappers.emptyWrapper());
    }


    default long count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(this.getBaseMapper().selectCount(queryWrapper));
    }


    default List<T> list(Wrapper<T> queryWrapper) {
        return this.getBaseMapper().selectList(queryWrapper);
    }


    default List<T> list(IPage<T> page, Wrapper<T> queryWrapper) {
        return this.getBaseMapper().selectList(page, queryWrapper);
    }


    default List<T> list() {
        return this.list(Wrappers.emptyWrapper());
    }


    default List<T> list(IPage<T> page) {
        return this.list(page, Wrappers.emptyWrapper());
    }


    default <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        return (E) this.getBaseMapper().selectPage(page, queryWrapper);
    }


    default <E extends IPage<T>> E page(E page) {
        return (E) this.page(page, Wrappers.emptyWrapper());
    }


    default List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper) {
        return this.getBaseMapper().selectMaps(queryWrapper);
    }


    default List<Map<String, Object>> listMaps(IPage<? extends Map<String, Object>> page, Wrapper<T> queryWrapper) {
        return this.getBaseMapper().selectMaps(page, queryWrapper);
    }


    default List<Map<String, Object>> listMaps() {
        return this.listMaps(Wrappers.emptyWrapper());
    }


    default List<Map<String, Object>> listMaps(IPage<? extends Map<String, Object>> page) {
        return this.listMaps(page, Wrappers.emptyWrapper());
    }


    default <E> List<E> listObjs() {
        return this.getBaseMapper().selectObjs(null);
    }


    default <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return this.listObjs(Wrappers.emptyWrapper(), mapper);
    }


    default <E> List<E> listObjs(Wrapper<T> queryWrapper) {
        return this.getBaseMapper().selectObjs(queryWrapper);
    }


    default <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return this.getBaseMapper().selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper)
                .collect(Collectors.toList());
    }


    default <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<T> queryWrapper) {
        return (E) this.getBaseMapper().selectMapsPage(page, queryWrapper);
    }


    default <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return (E) this.pageMaps(page, Wrappers.emptyWrapper());
    }


    default QueryChainWrapper<T> query() {
        return ChainWrappers.queryChain(this.getBaseMapper());
    }


    default LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(this.getBaseMapper(), this.getEntityClass());
    }


    default LambdaQueryChainWrapper<T> lambdaQuery(T entity) {
        return ChainWrappers.lambdaQueryChain(this.getBaseMapper(), entity);
    }


    default KtQueryChainWrapper<T> ktQuery() {
        return ChainWrappers.ktQueryChain(this.getBaseMapper(), this.getEntityClass());
    }


    default KtUpdateChainWrapper<T> ktUpdate() {
        return ChainWrappers.ktUpdateChain(this.getBaseMapper(), this.getEntityClass());
    }


    default UpdateChainWrapper<T> update() {
        return ChainWrappers.updateChain(this.getBaseMapper());
    }


    default LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return ChainWrappers.lambdaUpdateChain(this.getBaseMapper());
    }

    default BaseMapper<T> getBaseMapper() {
        return this;
    }

    default Class<T> getEntityClass() {
        // 通过反射获取泛型 T 的实际类型
        java.lang.reflect.Type type = this.getClass().getGenericInterfaces()[0];
        if (type instanceof java.lang.reflect.ParameterizedType parameterizedType) {
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        throw new RuntimeException("无法获取实体类类型");
    }
}
