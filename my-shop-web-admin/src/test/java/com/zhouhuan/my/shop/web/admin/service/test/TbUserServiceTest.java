package com.zhouhuan.my.shop.web.admin.service.test;

import com.zhouhuan.my.shop.domain.TbUser;
import com.zhouhuan.my.shop.web.admin.dao.TbUserDao;
import com.zhouhuan.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Description: //TODO
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/27 12:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserDao tbUserDao;

    @Autowired
    private TbUserService tbUserService;


    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserDao.selectAll();
        for (TbUser tbUser :
                tbUsers) {
            System.out.println(tbUser);
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setEmail("zhouhuan88888@163.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
        tbUser.setPhone("13124400119");
        tbUser.setUsername("hz031179");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testMd5() {
        System.out.println(DigestUtils.md5DigestAsHex("admin".getBytes()));
    }

    @Test
    public void testGetById() {
        TbUser tbUsers = tbUserService.getById((long) 43);
        System.out.println(tbUsers);

    }

}
