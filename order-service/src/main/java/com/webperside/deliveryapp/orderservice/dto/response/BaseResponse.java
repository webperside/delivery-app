package com.webperside.deliveryapp.orderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webperside.deliveryapp.orderservice.enums.ResponseMessages;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 4487922303496182391L;

    public enum BaseResponse_MessageType {
        SUCCESS, ERROR
    }

    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BaseResponse_Message {
        private String message;
        private BaseResponse_MessageType type;

        public static <E extends ResponseMessages> BaseResponse_Message of(E e) {
            return BaseResponse_Message.builder()
                    .message(e.message())
                    .type(e instanceof ResponseMessages.Success ? BaseResponse_MessageType.SUCCESS : BaseResponse_MessageType.ERROR)
                    .build();
        }

        public static <E extends ResponseMessages> BaseResponse_Message validation(String replace) {
            return BaseResponse_Message.builder()
                    .message(replace)
                    .build();
        }
    }

    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BaseResponse_ValidationMessage {
        private String field;
        private String message;

        public static BaseResponse_ValidationMessage validation(String field, String msg) {
            return BaseResponse_ValidationMessage.builder()
                    .field(field)
                    .message(msg)
                    .build();
        }
    }

    private String code;
    private HttpStatus status;
    private BaseResponse_Message message;
    private List<BaseResponse_ValidationMessage> validationMessages;
    private T data;

    public static <T, E extends ResponseMessages> BaseResponse<?> of(T data, E e, List<BaseResponse_ValidationMessage> validationMessages) {
        return BaseResponse.builder()
                .code(e.code())
                .status(e.status())
                .message(BaseResponse_Message.of(e))
                .validationMessages(validationMessages)
                .data(data)
                .build();
    }

    public static <T> BaseResponse<?> success(T data) {
        return of(data, ResponseMessages.Success.OK, null);
    }

    public static BaseResponse<?> success() {
        return of(null, ResponseMessages.Success.OK, null);
    }

    public static BaseResponse<?> validation(List<BaseResponse_ValidationMessage> validationMessages) {
        return of(null, ResponseMessages.Error.VALIDATION_ERROR, validationMessages);
    }
}
