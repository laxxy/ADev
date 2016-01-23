package com.audev.common.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by cosxt on 20.01.2016.
 */
@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

    @Autowired
    private WebAppConfig webAppConfig;

    /*@Bean
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder(definitionRegistry())
                .addFlowExecutionListener(new SecurityFlowExecutionListener(), "*")
                .build();
    }

    @Bean
    public FlowDefinitionRegistry definitionRegistry() {
        return getFlowDefinitionRegistryBuilder(flowBuilderServices())
                .setBasePath("/pages")
                .addFlowLocationPattern("/**///*-flow.xml").build();
    /*}

    @Bean
    public FlowBuilderServices flowBuilderServices() {
        return getFlowBuilderServicesBuilder()
                .setViewFactoryCreator(factoryCreator())
                .setValidator(validator())
                .setDevelopmentMode(true)
                .build();
    }

    @Bean
    public MvcViewFactoryCreator factoryCreator() {
        MvcViewFactoryCreator mvcViewFactoryCreator = new MvcViewFactoryCreator();
        mvcViewFactoryCreator.setViewResolvers(Collections.singletonList(this.webAppConfig.tilesViewResolver()));
        mvcViewFactoryCreator.setUseSpringBeanBinding(true);
        return mvcViewFactoryCreator;
    }*/

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
