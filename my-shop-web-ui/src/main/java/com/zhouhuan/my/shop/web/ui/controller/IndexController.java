package com.zhouhuan.my.shop.web.ui.controller;

import com.zhouhuan.my.shop.commons.utils.HttpClientUtils;
import com.zhouhuan.my.shop.commons.utils.MapperUtils;
import com.zhouhuan.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        String result = HttpClientUtils.doGet("http://localhost:8081/api/v1/contents/107");
        try {
            List<TbContent> tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
            for (TbContent tbContent : tbContents) {
                System.out.println(tbContent);
            }
            model.addAttribute("tbContents", tbContents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";


    }
}
