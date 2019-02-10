package com.zhouhuan.my.shop.web.admin.web.controller;

import com.zhouhuan.my.shop.commons.constant.ConstantUtils;
import com.zhouhuan.my.shop.commons.utils.CookieUtils;
import com.zhouhuan.my.shop.domain.TbUser;
import com.zhouhuan.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: //TODO
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/26 23:11
 */
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) {

        String cookieValue = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_NAME_USER_INFO);
        if (!StringUtils.isBlank(cookieValue)) {
            String[] userInfoArray = cookieValue.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("isRemember", true);
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest request, HttpServletResponse response, Model model) {

        boolean isRemember = request.getParameter("isRemember") == null ? false : true;

        TbUser tbUser = tbUserService.login(email, password);

        //不记住我
        if (!isRemember) {
            CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_NAME_USER_INFO);
        }

        //登录失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login(request);
        }

        //登录成功
        else {
            //记住我
            if (isRemember) {
                //用户信息存储一周
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_NAME_USER_INFO, String.format("%s:%s", email, password), 7 * 24 * 60 * 60);
            }
            //将登录信息放入会话
            request.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            //重定向
            return "redirect:/main";
        }

//        User user = userService.login(email, password);
//
//        //不记住我
//        if (!isRemember) {
//            CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_NAME_USER_INFO);
//        }
//
//        //登录失败
//        if (user == null) {
//            return login(request);
//        }
//        //登录成功
//        else {
//            //记住我
//            if (isRemember) {
//                //用户信息存储一周
//                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_NAME_USER_INFO, String.format("%s:%s", email, password), 7 * 24 * 60 * 60);
//            }
//            //将登录信息放入会话
//            request.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
//            //重定向
//            return "redirect:/main";
//        }

    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        //彻底清除session
        request.getSession().invalidate();
        return login(request);
    }
}
