package com.webperside.deliveryapp.orderservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateOrderResponse extends RepresentationModel<CreateOrderResponse> {

    Long id;

    public static CreateOrderResponse of(Long id){
        return CreateOrderResponse.builder().id(id).build();
    }

}
