package com.webperside.deliveryapp.orderservice.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

public interface ResponseMessages {

    String code();
    String message();
    HttpStatus status();

    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    enum Success implements ResponseMessages{
        OK("001","ok", HttpStatus.OK);

        String code;
        String message;
        HttpStatus status;

        @Override
        public String code() {return String.format("order-s%s",code);}

        @Override
        public String message() {return "response.success."+message;}

        @Override
        public HttpStatus status() {return status;}
    }

    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    enum Error implements ResponseMessages{
        INTERNAL_SERVER_ERROR("001","internalServer", HttpStatus.INTERNAL_SERVER_ERROR),
        VALIDATION_ERROR("002","validation", HttpStatus.BAD_REQUEST),
        NOT_FOUND_BY("003","notFound", HttpStatus.NOT_FOUND),
        LANGUAGE_NOT_SUPPORTED("004","languageNotSupported", HttpStatus.BAD_REQUEST);

        String code;
        String message;
        HttpStatus status;

        @Override
        public String code() {return String.format("order-e%s",code);}

        @Override
        public String message() {return "response.error." + message;}

        @Override
        public HttpStatus status() {return status;}
    }

}
