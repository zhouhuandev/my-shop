package com.zhouhuan.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容数据传输对象
 *
 * @Title:TbContentDTO
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 14:23
 */
@Data
public class TbContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
