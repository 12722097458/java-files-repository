package com.igeek.shop.entity;

import java.io.Serializable;
import java.util.List;

public class EasyUIResult<T> implements Serializable{

    private Integer total;
    private List<T> rows ;

    public EasyUIResult(){

    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyUIResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
