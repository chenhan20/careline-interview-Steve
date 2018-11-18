package com.careline.interview.test.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SteveAppConfigurer extends WebMvcConfigurerAdapter {
    @Bean
    public SteveInterceptor steveInterceptor() {
        return new SteveInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(steveInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
