package com.audev.common.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.webflow.JsfFlowHandlerAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;


import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by cosxt on 28.10.2015.
 */
@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan("com.audev.common")
public class WebAppConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private WebFlowConfig webFlowConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(60*1000L);
    }

    @Bean
    public InternalResourceViewResolver setupVR() {
        InternalResourceViewResolver ir = new InternalResourceViewResolver();

        ir.setPrefix("/pages/");
        ir.setSuffix(".jsp");
        ir.setViewClass(JstlView.class);

        return ir;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {

        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        tilesConfigurer.setCheckRefresh(true);

        return tilesConfigurer;
    }

    /*@Bean
    public FlowHandlerMapping flowHandlerMapping() {
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(1);
        mapping.setFlowRegistry(this.webFlowConfig.flowRegistry());
        mapping.setDefaultHandler(new UrlFilenameViewController());
        return mapping;
    }*/

    /*@Bean
    public FlowHandlerAdapter flowHandlerAdapter() {
        JsfFlowHandlerAdapter adapter = new JsfFlowHandlerAdapter();
        adapter.setFlowExecutor(this.webFlowConfig.flowExecutor());
        return adapter;
    }*/
}
