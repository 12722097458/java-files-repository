package com.igeek.ssm.domain;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private Date birthday;


    /*
        逻辑视图
    * */
    public String getBirth(){
        if (birthday != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return sdf.format(birthday);
        } else {
          return "";
        }
    }

}
