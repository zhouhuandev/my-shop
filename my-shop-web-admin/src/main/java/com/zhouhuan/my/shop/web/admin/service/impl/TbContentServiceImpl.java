package com.zhouhuan.my.shop.web.admin.service.impl;

import com.zhouhuan.my.shop.commons.dto.BaseResult;
import com.zhouhuan.my.shop.commons.validator.BeanValidator;
import com.zhouhuan.my.shop.domain.TbContent;
import com.zhouhuan.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.zhouhuan.my.shop.web.admin.dao.TbContentDao;
import com.zhouhuan.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        //未通过验证
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //通过验证
        else {
            tbContent.setUpdated(new Date());

            //新增
            if (tbContent.getId() == null) {
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }
            //编辑
            else {
                update(tbContent);
            }

            return BaseResult.success("保存内容信息成功！");
        }
    }


}
