package com.audev.common.Config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 *
 */
public class MainConfig implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ct = new AnnotationConfigWebApplicationContext();

        ct.register(WebAppConfig.class);
        ct.register(DataConfig.class);

        servletContext.addListener(new ContextLoaderListener(ct));

        ct.setServletContext(servletContext);

        DispatcherServlet ds = new DispatcherServlet(ct);

        ds.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", ds);

        dynamic.addMapping("/");
        dynamic.setAsyncSupported(true);
        dynamic.setLoadOnStartup(1);
    }
}
