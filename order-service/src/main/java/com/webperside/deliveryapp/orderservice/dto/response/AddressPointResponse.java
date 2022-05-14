package com.webperside.deliveryapp.orderservice.dto.response;

import com.webperside.deliveryapp.orderservice.entity.emmbedded.AddressPoint;
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

    public static AddressPointResponse fromEntity(AddressPoint ap){
        return AddressPointResponse.builder()
                .latitude(ap.getLatitude())
                .longitude(ap.getLongitude())
                .build();
    }
}
