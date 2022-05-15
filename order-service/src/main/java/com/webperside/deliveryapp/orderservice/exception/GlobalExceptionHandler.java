package com.webperside.deliveryapp.orderservice.exception;

import com.webperside.deliveryapp.orderservice.dto.response.BaseResponse;
import com.webperside.deliveryapp.orderservice.enums.ResponseMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //DataIntegrityViolationException

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<?>> handleBaseException(BaseException ex){
        return ResponseEntity.status(ex.getResponseMessage().status()).body(
                BaseResponse.error(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> handleException(Exception ex){
        return ResponseEntity.status(ResponseMessages.Error.INTERNAL_SERVER_ERROR.status()).body(
                BaseResponse.error(BaseException.of(ex))
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<BaseResponse.BaseResponse_ValidationMessage> validationsMessages = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationsMessages.add(BaseResponse.BaseResponse_ValidationMessage
                    .validation(error.getField(), error.getDefaultMessage()));
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            validationsMessages.add(BaseResponse.BaseResponse_ValidationMessage
                    .validation(error.getObjectName(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(status).body(BaseResponse.validation(validationsMessages));

    }
}
