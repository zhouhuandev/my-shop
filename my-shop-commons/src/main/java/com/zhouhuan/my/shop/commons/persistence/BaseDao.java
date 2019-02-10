package com.zhouhuan.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 数据访问层基类
 *
 * @Title:BaseDao
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/1 12:21
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询全部信息
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 添加
     *
     * @param entity
     */
    void insert(T entity);


    /**
     * 更新信息
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 通过id获取用户信息
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 批量删除
     *
     * @param idArray
     */
    void deleteMulti(String[] idArray);

    /**
     * 分页查询
     *
     * @param params，需要两个参数，start/记录开始的位置，length/每页记录数
     * @return
     */
    List<T> page(Map<String, Object> params);

    /**
     * 查询总笔数
     *
     * @return
     */
    int count(T entity);
}
