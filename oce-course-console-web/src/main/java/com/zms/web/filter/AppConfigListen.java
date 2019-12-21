package com.zms.web.filter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author zms
 * Date: 2019/11/23 23:53
 */
public class AppConfigListen implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute("adminContext",contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener:factory");
    }
}
