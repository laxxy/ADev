package com.audev.common.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by cosxt on 28.10.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.audev.common")
public class WebAppConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

    @Bean
    public InternalResourceViewResolver setupVR() {
        InternalResourceViewResolver ir = new InternalResourceViewResolver();

        ir.setPrefix("/assets/");
        ir.setSuffix(".jsp");
        ir.setViewClass(JstlView.class);

        return ir;
    }
}
