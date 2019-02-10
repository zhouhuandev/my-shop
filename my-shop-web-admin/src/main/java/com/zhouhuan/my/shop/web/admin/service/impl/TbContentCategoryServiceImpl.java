package com.zhouhuan.my.shop.web.admin.service.impl;

import com.zhouhuan.my.shop.commons.dto.BaseResult;
import com.zhouhuan.my.shop.commons.validator.BeanValidator;
import com.zhouhuan.my.shop.domain.TbContentCategory;
import com.zhouhuan.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.zhouhuan.my.shop.web.admin.dao.TbContentCategoryDao;
import com.zhouhuan.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 分类管理业务层
 *
 * @Title:TbContentCategoryServiceImpl
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/30 12:08
 */
@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);

        if (validator != null) {
            return BaseResult.fail(validator);
        } else {
            TbContentCategory parent = entity.getParent();
//            如果没有选择父节点则默认设置为根目录
            if (parent == null || parent.getId() == null) {
                // 0 代表根目录
                parent.setId(0L);
            }
            entity.setUpdated(new Date());

            //新增
            if (entity.getId() == null) {
                entity.setCreated(new Date());
                entity.setIsParent(false);

                //判断当前新节点有没有父级节点
                if (parent.getId() != 0L) {
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent != null) {
                        //为父节点设置 isParent 为 true
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }


                //父级节点为 0 ，表示为根目录
                else {
                    //根目录一定是父级目录
                    entity.setIsParent(true);
                }
                dao.insert(entity);
            }

            //修改
            else {
                update(entity);
            }
            return BaseResult.success("保存信息成功");
        }
    }
}
