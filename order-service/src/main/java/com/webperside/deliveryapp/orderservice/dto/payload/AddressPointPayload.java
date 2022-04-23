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

    @DecimalMin(value = "-90", message = "Latitude must be a number between -90 and 90")
    @DecimalMax(value = "90", message = "Latitude must be a number between -90 and 90")
    private Double latitude;
    @DecimalMin(value = "-180", message = "Longitude must a number between -180 and 180")
    @DecimalMax(value = "180", message = "Longitude must a number between -180 and 180")
    private Double longitude;

    public AddressPoint toEntity() {
        return AddressPoint.builder()
                .longitude(this.getLongitude())
                .latitude(this.getLatitude())
                .build();
    }
}
