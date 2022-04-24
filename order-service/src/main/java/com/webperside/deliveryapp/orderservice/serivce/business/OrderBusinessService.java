package com.webperside.deliveryapp.orderservice.serivce.business;

import com.webperside.deliveryapp.orderservice.dto.payload.CreateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.response.CreateOrderResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdDetailsResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdResponse;
import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.serivce.functional.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderBusinessService {

    private final OrderService orderService;

    public CreateOrderResponse createNewOrder(CreateOrderPayload createOrderPayload){
        return CreateOrderResponse.of(
                orderService.insertOrUpdate(createOrderPayload.toEntity()).getId()
        );
    }

    public OrderByIdResponse findById(Long id){
        return OrderByIdResponse.fromEntity(
                orderService.findById(id)
        );
    }

    public OrderByIdDetailsResponse findByIdDetails(Long id){
        Orders orders = orderService.findById(id);

        return null;
    }

}
