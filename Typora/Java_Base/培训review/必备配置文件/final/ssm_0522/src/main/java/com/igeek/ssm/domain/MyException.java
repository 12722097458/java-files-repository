package com.igeek.ssm.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyException extends Exception implements Serializable {
    private String msg;

    public MyException() {
    }

    public MyException(String msg) {
        this.msg = msg;
    }
}
