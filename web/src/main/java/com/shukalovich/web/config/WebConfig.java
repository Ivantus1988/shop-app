package com.shukalovich.web.config;

import com.shukalovich.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.shukalovich.web.util.PagesUtil.*;
import static com.shukalovich.web.util.PagesUtil.SUFFIX;

@Configuration
@Import(ServiceConfig.class)
@ComponentScan("com.shukalovich.web")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp(PREFIX, SUFFIX);
    }

}
