package com.zhouhuan.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {
    private T parent;
    private Boolean isParent;
}
