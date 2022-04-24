package com.webperside.deliveryapp.orderservice.serivce.functional;

import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.enums.ResponseMessages;
import com.webperside.deliveryapp.orderservice.exception.BaseException;
import com.webperside.deliveryapp.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Orders insertOrUpdate(Orders orders){
        return orderRepository.save(orders);
    }

    public Orders findById(Long id){
        return orderRepository.findById(id).orElseThrow(
                () -> BaseException.notFound(ResponseMessages.Error.NOT_FOUND_BY, Orders.class, Orders.Fields.id, id)
        );
    }
}
