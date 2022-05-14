package com.webperside.deliveryapp.orderservice.config;


import com.webperside.deliveryapp.orderservice.entity.Users;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<Users> {

    @Override
    public Optional<Users> getCurrentAuditor() {
        System.err.println("i am here");
        return Optional.of(Users.builder().id(1L).build());
    }
}