package com.zhouhuan.my.shop.web.admin.abstracts;

import com.zhouhuan.my.shop.commons.persistence.BaseTreeEntity;
import com.zhouhuan.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public abstract class AbstractBaseTreeController<T extends BaseTreeEntity, S extends BaseTreeService<T>> {
    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    /**
     * 跳转页面
     *
     * @return
     */
    public abstract String list(Model model);

    /**
     * 跳转到表单页
     *
     * @return
     */
    public abstract String form(T entity);

    /**
     * 保存信息
     *
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 树形结构
     *
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);

    /**
     * 排序
     *
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId   当前的父级类目 ID
     */
    protected void sortList(List<T> sourceList, List<T> targetList, Long parentId) {

        //遍历源集合，源集合的id等于传入的父id，则把源集合放入目标集合
        for (T sourceEntity :
                sourceList) {
            if (sourceEntity.getParent().getId().equals(parentId)) {
                targetList.add(sourceEntity);
                // 判断有没有子节点，有则继续追加
                if (sourceEntity.getIsParent()) {
                    for (T currentCategory :
                            sourceList) {
                        if (currentCategory.getParent().getId().equals(sourceEntity.getId())) {
                            sortList(sourceList, targetList, sourceEntity.getId());
                            break;
                        }
                    }
                }
            }

        }
    }


}
