package com.igeek.ssm.singleton;

/*
* 饿汉模式
* */
public class SingletonDemo02 {

    private static SingletonDemo02 instance = new SingletonDemo02();

    public SingletonDemo02() {
    }

    public static SingletonDemo02 getInstance(){
        return instance;
    }
}
