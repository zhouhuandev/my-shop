package com.zhouhuan.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhouhuan.my.shop.commons.persistence.BaseEntity;
import com.zhouhuan.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @Description: //TODO
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/27 12:45
 */
@Data
public class TbUser extends BaseEntity {
    @Length(min = 6, max = 20, message = "姓名长度必须介于 6 和 20 之间")
    private String username;
    //封装数据返回页面时排除password
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度必须介于 6 和 20 之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
}
