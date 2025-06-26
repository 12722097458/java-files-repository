package com.igeek.ssm.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class String2DateFormatConvert implements Converter<String,Date> {
    @Override
    public Date convert(String s) {
        Date date = new Date();
        String formatter = "yyyy/MM/dd";
        if (s.contains("-")){
            formatter = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
