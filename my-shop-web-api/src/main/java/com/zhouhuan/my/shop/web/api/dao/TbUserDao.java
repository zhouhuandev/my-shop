package com.zhouhuan.my.shop.web.api.dao;

import com.zhouhuan.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 会员管理
 *
 * @Title:TbUserDao
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 21:02
 */
@Repository
public interface TbUserDao {

    /**
     * 登录
     *
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
