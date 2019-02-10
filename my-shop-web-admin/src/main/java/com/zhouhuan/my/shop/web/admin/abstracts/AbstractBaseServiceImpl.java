package com.zhouhuan.my.shop.web.admin.abstracts;

import com.zhouhuan.my.shop.commons.dto.PageInfo;
import com.zhouhuan.my.shop.commons.persistence.BaseDao;
import com.zhouhuan.my.shop.commons.persistence.BaseEntity;
import com.zhouhuan.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部信息
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 更新信息
     *
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update(entity);
    }

    /**
     * 批量删除
     *
     * @param idArray
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] idArray) {
        dao.deleteMulti(idArray);
    }

    /**
     * 查询总笔数
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public int count(T entity) {
        return dao.count(entity);
    }


    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);

        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("length", length);
        map.put("pageParams", entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(map));

        return pageInfo;
    }
}
