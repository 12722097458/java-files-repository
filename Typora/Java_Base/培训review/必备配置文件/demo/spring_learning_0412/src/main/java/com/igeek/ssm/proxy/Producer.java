package com.igeek.ssm.proxy;

//生产者
public class Producer {
    //销售电脑
    public void saleProduct(Double money){
        System.out.println("生产并销售电脑，获得"+money);
    }
    //售后服务
    public void doService(Double money){
        System.out.println("售后服务，获得"+money);
    }
}
