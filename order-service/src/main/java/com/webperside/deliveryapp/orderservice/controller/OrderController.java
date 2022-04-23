package com.webperside.deliveryapp.orderservice.controller;

import com.webperside.deliveryapp.orderservice.dto.payload.CreateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.response.BaseResponse;
import com.webperside.deliveryapp.orderservice.serivce.business.OrderBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderBusinessService orderBusinessService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> createNewOrder(@Valid @RequestBody CreateOrderPayload payload){
        orderBusinessService.createNewOrder(payload);
        return ResponseEntity.ok(BaseResponse.success());
    }

}
