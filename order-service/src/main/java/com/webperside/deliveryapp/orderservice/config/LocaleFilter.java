package com.webperside.deliveryapp.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webperside.deliveryapp.orderservice.dto.response.BaseResponse;
import com.webperside.deliveryapp.orderservice.enums.Lang;
import com.webperside.deliveryapp.orderservice.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class LocaleFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Locale.setDefault(
                    Locale.forLanguageTag(
                            Lang.validate(request.getHeader("lang"))
                    )
            );
            filterChain.doFilter(request, response);
        } catch(BaseException ex){
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}
