package com.webperside.deliveryapp.orderservice.util;

import com.webperside.deliveryapp.orderservice.exception.BaseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessageUtil {

    private BaseException ex;
    private static final Map<BaseException.ExceptionType,
            Function<BaseException, BaseException.BaseException_BaseType>> asStringByType;

    static {
        asStringByType = new HashMap<>();
        asStringByType.put(BaseException.ExceptionType.DEFAULT, (b) -> new BaseException.BaseException_BaseType() {
            @Override
            public String asString(String msg) {
                return msg;
            }
        });
        asStringByType.put(BaseException.ExceptionType.NOT_FOUND, BaseException::getNotFoundDto);
        asStringByType.put(BaseException.ExceptionType.REPLACE_MESSAGE, BaseException::getReplaceMessageDto);
    }

    public String asString(){
        return asStringByType.getOrDefault(
                ex.getExceptionType(),
                asStringByType.get(BaseException.ExceptionType.DEFAULT)
        ).apply(ex).asString(ex.getResponseMessage().message());
    }

    public static ExceptionMessageUtil of(BaseException ex){
        return new ExceptionMessageUtil(ex);
    }
    
}
