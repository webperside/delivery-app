package com.webperside.deliveryapp.orderservice.enums;

import lombok.Getter;

public enum OrderStatus {
    PENDING,
    CANCELED,
    ASSIGNED_TO_COURIER,
    PICKED_UP,
    DELIVERED
}
