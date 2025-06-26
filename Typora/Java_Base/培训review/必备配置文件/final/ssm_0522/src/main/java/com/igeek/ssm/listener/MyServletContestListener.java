package com.igeek.ssm.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听器，监听ServletContestListener，但ServletContestListener创建时进行输出语句，一般可以用来加载资源
 */
@WebListener
public class MyServletContestListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String contextConfigLocation = (String) servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = "+contextConfigLocation);

        System.out.println("ServletContextEvent初始化。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextEvent销毁。。。");
    }
}
