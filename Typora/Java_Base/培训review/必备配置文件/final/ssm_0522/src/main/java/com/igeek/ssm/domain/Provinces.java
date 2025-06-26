package com.igeek.ssm.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Provinces implements Serializable {
    private int id;
    private String provinceid;
    private String provincename;
}
