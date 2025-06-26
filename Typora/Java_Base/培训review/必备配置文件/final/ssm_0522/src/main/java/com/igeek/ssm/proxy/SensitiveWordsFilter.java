package com.igeek.ssm.proxy;


import javax.servlet.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//@WebFilter(value = "/*")
public class SensitiveWordsFilter implements Filter {
    private List<String> list = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) {
        BufferedReader br = null;
        try {
            ServletContext servletContext = filterConfig.getServletContext();
            String realPath = servletContext.getRealPath("WEB-INF/words.txt");
            System.out.println(realPath);
            br = new BufferedReader(new FileReader(realPath));
            String str = "";
            while ((str = br.readLine())!=null){
                list.add(str);
            }
            System.out.println("敏感词："+list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("1111111111");
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //对getParameter()获取的值进行处理
                System.out.println("method.getName = "+method.getName());
                if ("getParameter".equals(method.getName())) {
                    String param = (String) method.invoke(servletRequest,args);
                    for (String str : list) {
                        if (param.contains(str)) {
                            param = param.replace(str, "***");
                        }
                    }
                    System.out.println("params = "+param);
                    return param;
                }
                return method.invoke(servletRequest,args);
            }
        });

        filterChain.doFilter(proxy_req,servletResponse);
        System.out.println(222222222);
    }

    @Override
    public void destroy() {

    }
}
