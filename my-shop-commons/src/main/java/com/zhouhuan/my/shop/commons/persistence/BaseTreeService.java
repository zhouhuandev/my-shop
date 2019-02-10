package com.zhouhuan.my.shop.commons.persistence;

import com.zhouhuan.my.shop.commons.dto.BaseResult;

import java.util.List;

/**
 * 通用的树形结构接口
 *
 * @Title:BaseTreeService
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/3 16:20
 */
public interface BaseTreeService<T extends BaseEntity> {
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
    BaseResult save(T entity);


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
     * 根据父级节点查询所有子节点
     *
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}
