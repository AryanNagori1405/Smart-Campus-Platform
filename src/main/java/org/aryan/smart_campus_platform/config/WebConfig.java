package org.aryan.smart_campus_platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class WebConfig implements PageableHandlerMethodArgumentResolverCustomizer {

    @Override
    public void customize(
            PageableHandlerMethodArgumentResolver resolver) {

        resolver.setMaxPageSize(100);
    }
}