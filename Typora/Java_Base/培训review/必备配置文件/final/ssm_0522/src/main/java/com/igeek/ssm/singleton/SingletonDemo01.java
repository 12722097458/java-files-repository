package com.igeek.ssm.singleton;

/*
* 懒汉模式
* */
public class SingletonDemo01 {

    private static SingletonDemo01 instance;

    public SingletonDemo01() {
    }

    public static SingletonDemo01 getInstance(){
        if (instance == null) {
            return new SingletonDemo01();
        }
        return instance;
    }
}
