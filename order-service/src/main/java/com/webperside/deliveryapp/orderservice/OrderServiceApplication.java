package com.webperside.deliveryapp.orderservice;

import com.webperside.deliveryapp.orderservice.entity.Orders;
import com.webperside.deliveryapp.orderservice.entity.emmbedded.AddressPoint;
import com.webperside.deliveryapp.orderservice.enums.OrderStatus;
import com.webperside.deliveryapp.orderservice.serivce.functional.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class OrderServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        AddressPoint startPoint = AddressPoint.builder()
                .latitude(3.0)
                .longitude(3.0)
                .build();
        AddressPoint desPoint = AddressPoint.builder()
                .latitude(2.0)
                .longitude(2.0)
                .build();
        Orders orders = new Orders();
        orders.setCourierId(1L);
        orders.setDescription("A");
        orders.setTitle("a");
        orders.setStatus(OrderStatus.PENDING);
        orders.setStartAddress(startPoint);
        orders.setDestinationAddress(desPoint);
//        orderService.insertOrUpdate(orders);
    }
}
