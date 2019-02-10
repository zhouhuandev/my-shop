package com.zhouhuan.my.shop.web.ui.api;

import com.zhouhuan.my.shop.commons.utils.HttpClientUtils;
import com.zhouhuan.my.shop.commons.utils.MapperUtils;
import com.zhouhuan.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * 内容管理接口
 *
 * @Title:ContentsApi
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 18:11
 */
public class ContentsApi {
    /**
     * 请求幻灯片
     *
     * @return
     */
    public static List<TbContent> ppt() {
        List<TbContent> tbContents = null;
        String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT);
        try {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }

}
