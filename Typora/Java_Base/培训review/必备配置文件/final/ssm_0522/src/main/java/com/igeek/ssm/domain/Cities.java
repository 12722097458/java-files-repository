package com.igeek.ssm.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cities implements Serializable {
    private int id;
    private String cityid;
    private String cityname;
    private String provinceid;
}
