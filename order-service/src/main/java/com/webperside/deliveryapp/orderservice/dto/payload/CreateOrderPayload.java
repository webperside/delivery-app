package com.webperside.deliveryapp.orderservice.dto.payload;

import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderPayload {

    @Size(min=5, max = 100, message = "Title size must be between 5 and 100")
    private String title;
    @Size(max = 255, message = "Description max size must be 255")
    private String description;
    private Long courierId;
    @NotNull(message = "Start address can not be null")
    private @Valid AddressPointPayload startAddress;
    @NotNull(message = "Destination address can not be null")
    private @Valid AddressPointPayload destinationAddress;

    public Orders toEntity(){
        return Orders.builder()
                .title(title)
                .description(description)
                .courierId(courierId)
                .startAddress(startAddress.toEntity())
                .destinationAddress(destinationAddress.toEntity())
                .status(OrderStatus.PENDING)
                .build();
    }
}
