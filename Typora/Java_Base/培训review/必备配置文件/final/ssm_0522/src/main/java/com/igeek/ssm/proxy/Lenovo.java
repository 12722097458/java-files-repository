package com.igeek.ssm.proxy;

public class Lenovo implements SaleComputer {
    @Override
    public String sale(double money) {
        System.out.println("买电脑花了"+money+"元！");
        return "联想电脑";
    }

    @Override
    public void show() {
        System.out.println("show...");
    }
}
