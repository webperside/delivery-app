package com.webperside.deliveryapp.orderservice.util;

import com.webperside.deliveryapp.orderservice.controller.OrderController;
import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class Hateoas {

    public static class Orders {
        private final static String INFO = "info";

        public static List<Link> info(Long id) {
            return Arrays.asList(
                    linkTo(
                            methodOn(OrderController.class).findById(id)
                    ).withRel(INFO),
                    linkTo(
                            methodOn(OrderController.class).findByIdDetails(id)
                    ).withRel(INFO)
            );
        }

    }

}
