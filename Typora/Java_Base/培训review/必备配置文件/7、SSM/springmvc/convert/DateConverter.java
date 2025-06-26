package com.igeek.shop.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String,Date>{

    private String pattern = "yyyy-MM-dd";

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Date convert(String source) {

        Date date = null;

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(source);
        }catch (ParseException ex){
            ex.printStackTrace();
        }

        return date;
    }
}
