package com.webperside.deliveryapp.orderservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Component
public class StaticMessageSource {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource manager) {
        messageSource = manager;
    }

    public static MessageSource INSTANCE() {
        return messageSource;
    }

    public static String get(String key){
        return INSTANCE().getMessage(key, null, Locale.getDefault());
    }
}
