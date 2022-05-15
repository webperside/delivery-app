package com.webperside.deliveryapp.orderservice.controller;

import com.webperside.deliveryapp.orderservice.dto.payload.CreateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.payload.UpdateOrderPayload;
import com.webperside.deliveryapp.orderservice.dto.response.BaseResponse;
import com.webperside.deliveryapp.orderservice.dto.response.CreateOrderResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdDetailsResponse;
import com.webperside.deliveryapp.orderservice.dto.response.OrderByIdResponse;
import com.webperside.deliveryapp.orderservice.serivce.business.OrderBusinessService;
import com.webperside.deliveryapp.orderservice.util.Hateoas;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderBusinessService orderBusinessService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> createNewOrder(@Valid @RequestBody CreateOrderPayload payload) {
        CreateOrderResponse newOrder = orderBusinessService.createNewOrder(payload);
        return ResponseEntity.ok(
                BaseResponse.success(
                        newOrder.add(Hateoas.Orders.info(newOrder.getId()))
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> updateOrder(@PathVariable("id") Long id,
                                                       @Valid @RequestBody UpdateOrderPayload payload) {
        orderBusinessService.updateOrder(id, payload);
        return ResponseEntity.ok(
                BaseResponse.success(
                        new RepresentationModel<>().add(Hateoas.Orders.info(id))
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> findById(@PathVariable("id") Long id) {
        OrderByIdResponse byId = orderBusinessService.findById(id);
        return ResponseEntity.ok(
                BaseResponse.success(
                        byId.add(
                                linkTo(
                                        methodOn(OrderController.class).findByIdDetails(byId.getId())
                                ).withRel("details")
                        )
                )
        );
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<BaseResponse<?>> findByIdDetails(@PathVariable("id") Long id) {
        OrderByIdDetailsResponse response = orderBusinessService.findByIdDetails(id);
        return ResponseEntity.ok(
                BaseResponse.success(
                        response.add(
                                linkTo(
                                        methodOn(OrderController.class).findById(response.getId())
                                ).withRel("orders")
                        )
                )
        );
    }

}
