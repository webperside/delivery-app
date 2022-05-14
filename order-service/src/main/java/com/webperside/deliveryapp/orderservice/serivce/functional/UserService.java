package com.webperside.deliveryapp.orderservice.serivce.functional;

import com.webperside.deliveryapp.orderservice.entity.Users;
import com.webperside.deliveryapp.orderservice.enums.ResponseMessages;
import com.webperside.deliveryapp.orderservice.exception.BaseException;
import com.webperside.deliveryapp.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> BaseException.notFound(
                        ResponseMessages.Error.NOT_FOUND_BY, Users.class, Users.Fields.id, userId)
                );
    }

}
