package com.zhouhuan.my.shop.web.admin.dao;

import com.zhouhuan.my.shop.commons.persistence.BaseTreeDao;
import com.zhouhuan.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

/**
 * 类目分类数据层
 *
 * @Title:TbContentCategoryDao
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/30 12:05
 */
@Repository
public interface TbContentCategoryDao extends BaseTreeDao<TbContentCategory> {

}
