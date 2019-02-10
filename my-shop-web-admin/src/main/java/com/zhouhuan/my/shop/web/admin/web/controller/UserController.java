package com.zhouhuan.my.shop.web.admin.web.controller;

import com.zhouhuan.my.shop.commons.dto.BaseResult;
import com.zhouhuan.my.shop.domain.TbUser;
import com.zhouhuan.my.shop.web.admin.abstracts.AbstractBaseController;
import com.zhouhuan.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Description: //TODO 用户控制器
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/27 15:45
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser, TbUserService> {


    /**
     * 结合user_form页面的表单里面使用的modelAttribute进行绑定数据
     * 自动获取用户信息，绑定到当前页面
     * 总在@RequestMapping标签前执行
     *
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;

        //id不为空，则从数据库中获取
        if (id != null) {
            tbUser = service.getById(id);
        }
        //否则创建新的对象,绑定到页面
        else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     * 用户列表跳转页面
     *
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "user_list";
    }

    /**
     * 跳转用户表单页面
     *
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }


    /**
     * 保存用户信息
     *
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbUser);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            //进行重定向，会清空所有的request的请求信息，故采用RedirectAttributes装载数据返回页面，前台使用el表达式取出
            return "redirect:/user/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }

    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除用户数据成功！");
        } else {
            baseResult = BaseResult.fail("删除数据失败！");
        }
        return baseResult;
    }

    /**
     * 显示用户详情信息
     *
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "user_detail";
    }
}
