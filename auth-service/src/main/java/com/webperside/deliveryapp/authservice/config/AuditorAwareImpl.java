package com.webperside.deliveryapp.authservice.config;


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        System.err.println("i am here");
        return Optional.of(1L);
    }
}