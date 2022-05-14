package com.webperside.deliveryapp.orderservice.enums;

import com.webperside.deliveryapp.orderservice.exception.BaseException;

import java.util.Optional;

public enum Lang {
    EN, AZ;

    public static String validate(String lang) {
        return Optional.ofNullable(lang).map((l) -> {
            for (Lang e : values()) {
                if (e.name().equalsIgnoreCase(l)) return e.name().toLowerCase();
            }
            throw BaseException.ofReplace(ResponseMessages.Error.LANGUAGE_NOT_SUPPORTED, lang);
        }).orElse(EN.name().toLowerCase());
    }

}
