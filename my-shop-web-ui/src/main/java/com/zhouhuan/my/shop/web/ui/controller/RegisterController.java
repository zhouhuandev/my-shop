package com.zhouhuan.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 注册控制器
 *
 * @Title:RegisterController
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 22:43
 */
@Controller
public class RegisterController {
    /**
     * 跳转注册页
     *
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }
}
