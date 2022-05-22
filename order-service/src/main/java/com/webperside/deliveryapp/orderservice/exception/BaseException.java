package com.webperside.deliveryapp.orderservice.exception;

import com.webperside.deliveryapp.orderservice.enums.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {

    ExceptionType exceptionType;
    ResponseMessages responseMessage;
    BaseException_NotFoundDto notFoundDto;
    BaseException_ReplaceMessageDto replaceMessageDto;

    public enum ExceptionType {
        DEFAULT, NOT_FOUND, REPLACE_MESSAGE
    }

    public abstract static class BaseException_BaseType {
        public abstract String asString(String msg);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class BaseException_NotFoundDto extends BaseException_BaseType {
        Class<?> clazz;
        Map<String, Object> fields;

        public static BaseException_NotFoundDto of(Class<?> clazz, String field, Object value) {
            return BaseException_NotFoundDto.builder().clazz(clazz).fields(Map.of(field, value)).build();
        }

        public static BaseException_NotFoundDto of(Class<?> clazz, Map<String, Object> fields) {
            return BaseException_NotFoundDto.builder().clazz(clazz).fields(fields).build();
        }

        @Override
        public String asString(String base) {
            return String.format(base,
                    this.getClazz().getSimpleName(),
                    fields.entrySet().stream()
                            .map((e) -> String.format("%s=%s", e.getKey(), e.getValue()))
                            .collect(Collectors.joining(","))
            );
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class BaseException_ReplaceMessageDto extends BaseException_BaseType {
        String replace;

        public static BaseException_ReplaceMessageDto of(String replace) {
            return BaseException_ReplaceMessageDto.builder().replace(replace).build();
        }

        @Override
        public String asString(String msg) {
            return String.format(msg, replace);
        }
    }

    public static BaseException of(ResponseMessages message) {
        return BaseException.builder().exceptionType(ExceptionType.DEFAULT)
                .responseMessage(message).build();
    }

    public static BaseException of(Exception ex) {
        return ofReplace(ResponseMessages.Error.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    public static BaseException ofReplace(ResponseMessages message, String replace) {
        return BaseException.builder().exceptionType(ExceptionType.REPLACE_MESSAGE)
                .responseMessage(message).replaceMessageDto(BaseException_ReplaceMessageDto.of(replace)).build();
    }

    public static BaseException notFound(ResponseMessages message, Class<?> clazz, String field, Object value) {
        return BaseException.builder().exceptionType(ExceptionType.NOT_FOUND)
                .responseMessage(message).notFoundDto(BaseException_NotFoundDto.of(clazz, field, value)).build();
    }

}
