package com.zhouhuan.my.shop.web.admin.service;

import com.zhouhuan.my.shop.commons.persistence.BaseService;
import com.zhouhuan.my.shop.domain.TbUser;

/**
 * @Description: //TODO
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/27 12:49
 */
public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户登录
     *
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);
}
