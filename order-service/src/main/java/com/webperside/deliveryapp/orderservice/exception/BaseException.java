package com.webperside.deliveryapp.orderservice.exception;

import com.webperside.deliveryapp.orderservice.enums.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {

    ResponseMessages responseMessage;
    BaseException_NotFoundDto notFoundDto;

    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class BaseException_NotFoundDto {
        Class<?> clazz;
        String field;
        Object value;

        public static BaseException_NotFoundDto of(Class<?> clazz, String field, Object value) {
            return BaseException_NotFoundDto.builder().clazz(clazz).field(field).value(value).build();
        }

        public String asString(String base) {
            return String.format(base, this.getClazz().getSimpleName(), this.getField(), this.getValue());
        }
    }

    public static BaseException of(ResponseMessages message) {
        return BaseException.builder().responseMessage(message).build();
    }

    public static BaseException notFound(ResponseMessages message, Class<?> clazz, String field, Object value) {
        return BaseException.builder().responseMessage(message).notFoundDto(BaseException_NotFoundDto.of(clazz, field, value)).build();
    }

}
