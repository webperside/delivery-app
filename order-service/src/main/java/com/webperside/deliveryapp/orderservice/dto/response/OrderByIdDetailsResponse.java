package com.webperside.deliveryapp.orderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OrderByIdDetailsResponse_User extends RepresentationModel<OrderByIdDetailsResponse_User> {
        Long id;
        String fullName;
    }

}
