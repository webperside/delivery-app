package com.webperside.deliveryapp.orderservice.controller;

import com.webperside.deliveryapp.orderservice.dto.payload.CreateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.response.BaseResponse;
import com.webperside.deliveryapp.orderservice.dto.response.CreateOrderResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdResponse;
import com.webperside.deliveryapp.orderservice.serivce.business.OrderBusinessService;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RequestMapping("/")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderBusinessService orderBusinessService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> createNewOrder(@Valid @RequestBody CreateOrderPayload payload){
        CreateOrderResponse newOrder = orderBusinessService.createNewOrder(payload);
        return ResponseEntity.ok(
                BaseResponse.success(
                        newOrder.add(
                                linkTo(
                                        methodOn(OrderController.class).findById(newOrder.getId())
                                ).withSelfRel()
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> findById(@PathVariable("id") Long id){
        OrderByIdResponse byId = orderBusinessService.findById(id);
        return ResponseEntity.ok(
                BaseResponse.success(
                        byId
//                                .add(
//                                linkTo(
//                                        methodOn(OrderController.class).findById();
//                                )
//                        )
                )
        );
    }


}
