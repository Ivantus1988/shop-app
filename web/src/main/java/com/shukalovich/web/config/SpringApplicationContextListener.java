package com.shukalovich.web.config;

import com.shukalovich.config.ServiceConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class SpringApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ServiceConfig.class);

        sce.getServletContext().setAttribute("applicationContext", ac);
    }
}

