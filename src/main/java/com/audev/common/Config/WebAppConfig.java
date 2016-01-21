package com.audev.common.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.conditionalcomments.dialect.ConditionalCommentsDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring4.web.view.FlowAjaxThymeleafTilesView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.AjaxThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.LinkedHashSet;
import java.util.Set;

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
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(30*1000L);
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

    @Bean
    public AjaxThymeleafViewResolver tilesViewResolver() {
        AjaxThymeleafViewResolver viewResolver = new AjaxThymeleafViewResolver();
        viewResolver.setViewClass(FlowAjaxThymeleafTilesView.class);
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){

        Set<IDialect> dialects = new LinkedHashSet<>();
        dialects.add(new TilesDialect());
        dialects.add(new SpringSecurityDialect());
        dialects.add(new ConditionalCommentsDialect());

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setAdditionalDialects(dialects);
        return templateEngine;
    }

    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/pages/");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }
}
