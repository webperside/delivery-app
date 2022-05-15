package com.webperside.deliveryapp.orderservice.serivce.business;

import com.webperside.deliveryapp.orderservice.dto.payload.CreateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.payload.UpdateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.response.CreateOrderResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdDetailsResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdResponse;
import com.webperside.deliveryapp.orderservice.entity.Users;
import com.webperside.deliveryapp.orderservice.serivce.functional.OrderService;
import com.webperside.deliveryapp.orderservice.serivce.functional.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderBusinessService {

    private final OrderService orderService;
    private final UserService userService;

    public CreateOrderResponse createNewOrder(CreateOrderPayload createOrderPayload) {
        return CreateOrderResponse.of(
                orderService.insertOrUpdate(
                        createOrderPayload.toEntity(
                                findCourierIfIdNotNull(createOrderPayload.getCourierId())
                        )).getId()
        );
    }

    public void updateOrder(Long id, UpdateOrderPayload updateOrderPayload) {
        orderService.insertOrUpdate(
                updateOrderPayload.updateEntity(
                        orderService.findById(id),
                        findCourierIfIdNotNull(updateOrderPayload.getCourierId())
                )
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

    private Users findCourierIfIdNotNull(Long courierId) {
        return Optional.ofNullable(
                courierId
        ).map(userService::findById).orElse(null);
    }
}
