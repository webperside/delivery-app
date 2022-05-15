package com.webperside.deliveryapp.orderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.entity.Users;
import com.webperside.deliveryapp.orderservice.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderByIdDetailsResponse extends RepresentationModel<OrderByIdDetailsResponse> {

    Long id;
    String title;
    String description;
    OrderStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime modifiedAt;

    AddressPointResponse startAddress;
    AddressPointResponse destinationAddress;

    OrderByIdDetailsResponse_User courier;
    OrderByIdDetailsResponse_User createdBy;

    public static OrderByIdDetailsResponse fromEntity(Orders orders) {
        return OrderByIdDetailsResponse.builder()
                .id(orders.getId())
                .title(orders.getTitle())
                .description(orders.getDescription())
                .status(orders.getStatus())
                .createdAt(orders.getCreatedAt())
                .modifiedAt(orders.getModifiedAt())
                .startAddress(AddressPointResponse.fromEntity(orders.getStartAddress()))
                .destinationAddress(AddressPointResponse.fromEntity(orders.getDestinationAddress()))
                .courier(Optional.ofNullable(orders.getCourier()).map(OrderByIdDetailsResponse_User::fromEntity).orElse(null))
                .createdBy(Optional.ofNullable(orders.getCreatedBy()).map(OrderByIdDetailsResponse_User::fromEntity).orElse(null))
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OrderByIdDetailsResponse_User {
        Long id;
        String fullName;

        public static OrderByIdDetailsResponse_User fromEntity(Users user) {
            return OrderByIdDetailsResponse_User.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .build();
        }
    }

}
