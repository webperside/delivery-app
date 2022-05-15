package com.webperside.deliveryapp.orderservice.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityUtil<T> {

    private T target;

    public static <T> EntityUtil<T> of(T t) {
        return new EntityUtil<>(t);
    }

    public <U> void updateField(BiConsumer<T, U> consumer, U value) {
        if (value != null) consumer.accept(target, value);
    }

    public <U, R> void updateField(BiConsumer<T, U> consumer, R value, Function<R, U> function) {
        updateField(consumer, Optional.ofNullable(value).map(function).orElse(null));
    }

}
