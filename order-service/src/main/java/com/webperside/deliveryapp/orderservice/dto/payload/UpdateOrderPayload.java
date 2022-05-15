package com.webperside.deliveryapp.orderservice.dto.payload;

import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.entity.Users;
import com.webperside.deliveryapp.orderservice.enums.OrderStatus;
import com.webperside.deliveryapp.orderservice.util.EntityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderPayload {

    @Size(min = 5, max = 100, message = "{orders.title.size}")
    private String title;
    @Size(max = 255, message = "{orders.description.size}")
    private String description;
    private Long courierId;
    private @Valid AddressPointPayload startAddress;
    private @Valid AddressPointPayload destinationAddress;
    @NotNull(message = "{orders.status.notNull}")
    private OrderStatus status;

    public Orders updateEntity(Orders order, Users courier) {
        EntityUtil<Orders> ordersEntityUtil = EntityUtil.of(order);
        ordersEntityUtil.updateField(Orders::setTitle, this.getTitle());
        ordersEntityUtil.updateField(Orders::setDescription, this.getDescription());
        ordersEntityUtil.updateField(Orders::setCourier, courier);
        ordersEntityUtil.updateField(Orders::setStartAddress, startAddress, AddressPointPayload::toEntity);
        ordersEntityUtil.updateField(Orders::setDestinationAddress, destinationAddress, AddressPointPayload::toEntity);
        ordersEntityUtil.updateField(Orders::setStatus, status);
        return order;
    }
}
