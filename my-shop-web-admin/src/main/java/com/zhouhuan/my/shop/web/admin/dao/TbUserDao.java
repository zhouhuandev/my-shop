package com.zhouhuan.my.shop.web.admin.dao;

import com.zhouhuan.my.shop.commons.persistence.BaseDao;
import com.zhouhuan.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @Description: //TODO
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/27 12:44
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 根据邮箱查询用户信息
     *
     * @param eamil
     * @return
     */
    TbUser getByEmail(String eamil);
}
