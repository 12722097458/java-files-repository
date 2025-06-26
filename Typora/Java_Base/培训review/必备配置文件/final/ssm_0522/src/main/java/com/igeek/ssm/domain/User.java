package com.igeek.ssm.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private int userid;
    private String username;
    private String password;
    private Date birthday;
}
