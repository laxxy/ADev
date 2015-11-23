package com.audev.common.Config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by cosxt on 20.10.2015.
 */
public class MainConfig implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ct = new AnnotationConfigWebApplicationContext();

        ct.register(WebAppConfig.class);

        servletContext.addListener(new ContextLoaderListener(ct));

        ct.setServletContext(servletContext);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ct));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }
}
