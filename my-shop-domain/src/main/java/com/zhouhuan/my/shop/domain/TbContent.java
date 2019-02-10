package com.zhouhuan.my.shop.domain;

import com.zhouhuan.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 内容管理
 *
 * @Title:TbContent
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/30 19:00
 */
@Data
public class TbContent extends BaseEntity {

    //内容标题
    @Length(min = 1, max = 20, message = "标题长度介于1-20之间")
    private String title;
    //子标题
    @Length(min = 1, max = 20, message = "子标题长度介于1-20之间")
    private String subTitle;
    //标题描述
    @Length(min = 1, max = 50, message = "标题描述长度介于1-50之间")
    private String titleDesc;
    //链接
    private String url;
    //图片绝对路径
    private String pic;
    //图片2
    private String pic2;
    //内容
    @Length(min = 1, message = "内容不能为空！")
    private String content;

    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;

}
