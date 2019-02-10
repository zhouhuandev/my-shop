package com.zhouhuan.my.shop.commons.dto;

import com.zhouhuan.my.shop.commons.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据传输对象
 *
 * @Title:PageInfo
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/29 14:43
 */
public class PageInfo<T extends BaseEntity> implements Serializable {
    //必须，Datatables发送的draw是多少那么服务器就返回多少。 这里注意，作者出于安全的考虑，强烈要求把这个转换为整形，即数字后再返回，而不是纯粹的接受然后返回，这是 为了防止跨站脚本（XSS）攻击。
    private int draw;
    //必须，即没有过滤的记录数（数据库里总共记录数）
    private int recordsTotal;
    //必须，过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
    private int recordsFiltered;
    private List<T> data;
    private String error;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
