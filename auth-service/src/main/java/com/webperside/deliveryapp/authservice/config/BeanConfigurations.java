package com.webperside.deliveryapp.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class BeanConfigurations {

    @Bean
    public AuditorAware<Long> auditorProvider(){
        return new AuditorAwareImpl();
    }

}
