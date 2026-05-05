package com.example.servlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // Static resources are served by the container default servlet.
        // Keep listener for future global initialization if needed.
        context.log("Web application initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 終了時の処理（必要な場合）
    }
}
