package com.webperside.deliveryapp.orderservice.repository;

import com.webperside.deliveryapp.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
