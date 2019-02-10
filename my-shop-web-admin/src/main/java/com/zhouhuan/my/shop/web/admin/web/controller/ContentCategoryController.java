package com.zhouhuan.my.shop.web.admin.web.controller;

import com.zhouhuan.my.shop.commons.dto.BaseResult;
import com.zhouhuan.my.shop.domain.TbContentCategory;
import com.zhouhuan.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.zhouhuan.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类管理控制
 *
 * @Title:ContentCategoryController
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/30 12:12
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory, TbContentCategoryService> {

    /**
     * 结合user_form页面的表单里面使用的modelAttribute进行绑定数据
     * 自动获取用户信息，绑定到当前页面
     * 总在@RequestMapping标签前执行
     *
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id) {
        TbContentCategory tbContentCategory = null;

        //id不为空，则从数据库中获取
        if (id != null) {
            tbContentCategory = service.getById(id);
        }
        //否则创建新的对象,绑定到页面
        else {
            tbContentCategory = new TbContentCategory();
        }
        return tbContentCategory;
    }

    /**
     * 跳转分类管理页面
     *
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = service.selectAll();

        //排序
        sortList(sourceList, targetList, 0L);
        model.addAttribute("tbContentCategories", targetList);
        return "content_category_list";
    }

    /**
     * 跳转分类表单页面
     *
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }

    /**
     * 保存
     *
     * @param tbContentCategory
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContentCategory);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form(tbContentCategory);
        }
    }

    /**
     * 树形结构
     *
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id) {
        if (id == null) {
            id = 0L;
        }
        return service.selectByPid(id);
    }
}
