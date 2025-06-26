package com.igeek.shop.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * 分页组件
 */
public class PageUtils<T> implements Serializable {

    // 显示第几页的信息
    private int pageNo = 1;
    // 每页显示多少条信息
    private int pageSize = 10;

    // 总记录数
    private int totalCount = 0;
    // 总页数
    private int totalPages = 0;

    // 分页导航栏
    private int[] bar;

    // 每页的实际数据集合
    private List<T> data = new ArrayList<T>();

    public PageUtils() {

    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    // 计算获取分页条
    public int[] getBar() {
        // 确定好开头和结尾
        int start=1;
        int stop=totalPages;
        // 少于10页
        if (totalPages < 10) {
            start = 1;
            stop = totalPages;

        } else {
            //pageNo=16
            //11,16,20
            start = pageNo - 5;
            stop = pageNo + 4;
            if (stop >= totalPages) {
                //totalPages=18
                //9,16,18
                start = totalPages - 9;
                stop = totalPages;
            }

            //pageNo=4
            //1,4,10
            if (pageNo - 5 <= 0) {
                start = 1;
                stop = 10;
            }

        }
        bar = new int[stop - start + 1];
        int index = 0;
        for (int i = start; i <= stop; i++) {
            bar[index++] = i;
        }
        return bar;
    }

}
