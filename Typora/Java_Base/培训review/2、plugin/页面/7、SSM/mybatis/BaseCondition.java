package com.igeek.shop.entity;

import java.io.Serializable;

public class BaseCondition<T> implements Serializable {

    private T conditionBean;

    private Integer beginIndex;
    private Integer count;

    public BaseCondition() {
    }

    public T getConditionBean() {
        return conditionBean;
    }

    public void setConditionBean(T conditionBean) {
        this.conditionBean = conditionBean;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
