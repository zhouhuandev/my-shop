package com.zhouhuan.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员传输对象
 *
 * @Title:TbUserDTO
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 21:11
 */
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
