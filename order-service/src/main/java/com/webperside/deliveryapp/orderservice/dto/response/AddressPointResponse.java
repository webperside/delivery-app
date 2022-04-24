package com.webperside.deliveryapp.orderservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressPointResponse {

    Double latitude;
    Double longitude;
}
