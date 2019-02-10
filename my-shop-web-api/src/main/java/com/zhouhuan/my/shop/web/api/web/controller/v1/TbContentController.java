package com.zhouhuan.my.shop.web.api.web.controller.v1;

import com.zhouhuan.my.shop.commons.dto.BaseResult;
import com.zhouhuan.my.shop.domain.TbContent;
import com.zhouhuan.my.shop.web.api.service.TbContentService;
import com.zhouhuan.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * api内容控制器
 * 里面使用的 Spring 的配置文件 并且使用 Spring 表达式引入值
 *
 * @Title:TbContentController
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 14:50
 */
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;
        if (id == null) {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * 根据类别 ID 查询内容
     * 使用了路径参数
     *
     * @param categoryId
     * @return
     * @PathVariable 路径参数配置
     * @RequestParam 是否是必须
     */
    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(@PathVariable(value = "category_id") Long categoryId) {
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);

        if (tbContents != null && tbContents.size() > 0) {
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                //反射工具类
                BeanUtils.copyProperties(tbContent, dto);
                tbContentDTOS.add(dto);
            }
        }
        return BaseResult.success("成功", tbContentDTOS);
    }
}
