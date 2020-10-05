package com.epam.marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.marketplace")
public class MvcConfig implements WebMvcConfigurer {
    private static final String VIEW_RESOLVER_PREFIX = "/static/html/";
    private static final String VIEW_RESOLVER_SUFFIX = ".html";
    private static final String RESOURCE_HANDLER_PATH_PATTERN = "/static/**";
    private static final String RESOURCE_LOCATION = "/WEB-INF/static/";


    @Bean(name = "validator")
    public Validator getBeanValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        internalResourceViewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        return internalResourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(RESOURCE_HANDLER_PATH_PATTERN)
                .addResourceLocations(RESOURCE_LOCATION);
    }
}
