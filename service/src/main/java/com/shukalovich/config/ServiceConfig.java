package com.shukalovich.config;

import com.shukalovich.database.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DatabaseConfig.class)
@ComponentScan("com.shukalovich.service")
@Configuration
public class ServiceConfig {
}
