package com.webperside.deliveryapp.authservice.enums;

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
        OK("001","Successfully", HttpStatus.OK);


        String code;
        String message;
        HttpStatus status;

        @Override
        public String code() {return String.format("order-s%s",code);}

        @Override
        public String message() {return message;}

        @Override
        public HttpStatus status() {return status;}
    }

    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    enum Error implements ResponseMessages{
        ERROR("001","Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
        VALIDATION_ERROR("002","Validation Error", HttpStatus.BAD_REQUEST),
        NOT_FOUND_BY("003","%s can't find by %s=%s", HttpStatus.NOT_FOUND);

        String code;
        String message;
        HttpStatus status;

        @Override
        public String code() {return String.format("order-e%s",code);}

        @Override
        public String message() {return message;}

        @Override
        public HttpStatus status() {return status;}
    }

}
