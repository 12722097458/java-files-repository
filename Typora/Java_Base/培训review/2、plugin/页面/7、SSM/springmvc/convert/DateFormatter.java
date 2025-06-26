package com.igeek.shop.convert;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {

    private String pattern = "yyyy-MM-dd";
    private  SimpleDateFormat sdf = null;

    public void setPattern(String pattern) {
        this.pattern = pattern;
        sdf = new SimpleDateFormat(this.pattern);
    }

    //从页面的字符串数据到控制器的目标数据
    @Override
    public Date parse(String source, Locale locale) throws ParseException {
        Date date = null;

        try {
            date = sdf.parse(source);
        }catch (ParseException ex){
            ex.printStackTrace();
        }

        return date;
    }

    //控制器中的数据到页面格式化显示的数据,页面中得使用springmvc的方式(spring的标签库)进行输出
    @Override
    public String print(Date date, Locale locale) {
        return sdf.format(date);
    }
}
