package com.webperside.deliveryapp.orderservice.entity.emmbedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressPoint {

    private Double latitude;
    private Double longitude;

}
