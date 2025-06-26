package com.igeek.ssm.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormatUtils implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        if ("".equals(source) || source == null) {
            throw new RuntimeException("请输入相关数据！");
        }
        String format = "";
        if (source.contains("-")) {
            format="yyyy-MM-dd";
        } else if (source.contains("/")) {
            format="yyyy/MM/dd";
        }
        DateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(source);
        } catch (Exception e) {
            throw new RuntimeException("日期转换出错！");
        }
    }
}
