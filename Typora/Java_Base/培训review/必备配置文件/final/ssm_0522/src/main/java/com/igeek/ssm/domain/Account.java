package com.igeek.ssm.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
    private int uid;
    private String uname;
    private double umoney;
}
