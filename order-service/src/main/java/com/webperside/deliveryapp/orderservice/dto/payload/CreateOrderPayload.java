package com.webperside.deliveryapp.orderservice.dto.payload;

import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.entity.Users;
import com.webperside.deliveryapp.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderPayload {

    @Size(min = 5, max = 100, message = "{orders.title.size}")
    private String title;
    @Size(max = 255, message = "{orders.description.size}")
    private String description;
    private Long courierId;
    @NotNull(message = "{orders.address.notNull.start}")
    private @Valid AddressPointPayload startAddress;
    @NotNull(message = "{orders.address.notNull.destination}")
    private @Valid AddressPointPayload destinationAddress;

    public Orders toEntity(Users courier) {
        return Orders.builder()
                .title(title)
                .description(description)
                .courier(courier)
                .startAddress(startAddress.toEntity())
                .destinationAddress(destinationAddress.toEntity())
                .status(OrderStatus.PENDING)
                .build();
    }
}
