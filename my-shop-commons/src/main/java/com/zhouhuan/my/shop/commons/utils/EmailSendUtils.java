package com.zhouhuan.my.shop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮件发送工具类
 *
 * @Title:EmailSendUtils
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 21:51
 */
public class EmailSendUtils {
    @Autowired
    private Email email;

    public void send(String subject, String msg, String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
