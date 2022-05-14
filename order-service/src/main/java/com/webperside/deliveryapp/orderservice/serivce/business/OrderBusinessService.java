package com.webperside.deliveryapp.orderservice.serivce.business;

import com.webperside.deliveryapp.orderservice.dto.payload.CreateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.response.CreateOrderResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdDetailsResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdResponse;
import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.serivce.functional.OrderService;
import com.webperside.deliveryapp.orderservice.serivce.functional.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderBusinessService {

    private final OrderService orderService;
    private final UserService userService;

    public CreateOrderResponse createNewOrder(CreateOrderPayload createOrderPayload) {
        return CreateOrderResponse.of(
                orderService.insertOrUpdate(
                        createOrderPayload.toEntity(
                                userService.findById(createOrderPayload.getCourierId())
                        )).getId()
        );
    }

    public OrderByIdResponse findById(Long id) {
        return OrderByIdResponse.fromEntity(
                orderService.findById(id)
        );
    }

    public OrderByIdDetailsResponse findByIdDetails(Long id) {
        return OrderByIdDetailsResponse.fromEntity(orderService.findById(id));
    }

}
