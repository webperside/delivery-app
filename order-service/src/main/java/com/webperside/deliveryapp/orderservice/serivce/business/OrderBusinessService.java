package com.webperside.deliveryapp.orderservice.serivce.business;

import com.webperside.deliveryapp.orderservice.dto.payload.CreateOrderPayload;
import com.webperside.deliveryapp.orderservice.serivce.functional.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderBusinessService {

    private final OrderService orderService;

    public void createNewOrder(CreateOrderPayload createOrderPayload){
        orderService.insertOrUpdate(createOrderPayload.toEntity());
    }

}
