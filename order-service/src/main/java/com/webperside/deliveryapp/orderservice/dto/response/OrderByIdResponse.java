package com.webperside.deliveryapp.orderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderByIdResponse extends RepresentationModel<OrderByIdResponse> {

    Long id;
    String title;
    String description;
    OrderStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime createdAt;

    public static OrderByIdResponse fromEntity(Orders orders) {
        return OrderByIdResponse.builder()
                .id(orders.getId())
                .title(orders.getTitle())
                .description(orders.getDescription())
                .status(orders.getStatus())
                .createdAt(orders.getCreatedAt())
                .build();
    }

}
