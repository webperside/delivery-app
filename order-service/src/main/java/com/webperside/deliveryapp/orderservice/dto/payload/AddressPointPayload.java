package com.webperside.deliveryapp.orderservice.dto.payload;

import com.webperside.deliveryapp.orderservice.entity.emmbedded.AddressPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressPointPayload {

    @DecimalMin(value = "-90", message = "{orders.address.lat.size}")
    @DecimalMax(value = "90", message = "{orders.address.lat.size}")
    private Double latitude;
    @DecimalMin(value = "-180", message = "{orders.address.long.size}")
    @DecimalMax(value = "180", message = "{orders.address.long.size}")
    private Double longitude;

    public AddressPoint toEntity() {
        return AddressPoint.builder()
                .longitude(this.getLongitude())
                .latitude(this.getLatitude())
                .build();
    }
}
