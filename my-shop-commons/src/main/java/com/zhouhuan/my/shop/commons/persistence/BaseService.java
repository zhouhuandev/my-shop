package com.zhouhuan.my.shop.commons.persistence;

import com.zhouhuan.my.shop.commons.dto.BaseResult;
import com.zhouhuan.my.shop.commons.dto.PageInfo;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    /**
     * 查询信息
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 新增用户
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
     * 批量删除
     *
     * @param idArray
     */
    void deleteMulti(String[] idArray);

    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总笔数
     *
     * @return
     */
    int count(T entity);
}
