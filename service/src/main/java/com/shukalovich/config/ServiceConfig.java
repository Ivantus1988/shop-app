package com.shukalovich.config;

import com.shukalovich.database.config.DatabaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(DatabaseConfig.class)
@ComponentScan("com.shukalovich.service")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
