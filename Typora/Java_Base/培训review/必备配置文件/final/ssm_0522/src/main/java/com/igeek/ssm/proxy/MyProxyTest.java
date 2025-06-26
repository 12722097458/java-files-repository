package com.igeek.ssm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyTest {
    public static void main(String[] args) {
        final Lenovo lenovo = new Lenovo();
        SaleComputer lenovo_proxy = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //对方法执行过程及逆行了加强
                System.out.println("进行了加强。。。");
                if ("sale".equals(method.getName())){
                    //对参数进行了加强
                    double money = (double) args[0] * 0.8;
                    Object invoke = method.invoke(lenovo, money);
                    //对返回值进行了加强
                    return invoke+"+一个鼠标";
                }else {
                    Object invoke = method.invoke(lenovo, args);
                    return invoke;
                }
            }
        });
        String sale = lenovo_proxy.sale(10000);
        System.out.println(sale);
        lenovo_proxy.show();

    }
}
