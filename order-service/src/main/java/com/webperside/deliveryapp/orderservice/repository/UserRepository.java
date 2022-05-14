package com.webperside.deliveryapp.orderservice.repository;

import com.webperside.deliveryapp.orderservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
