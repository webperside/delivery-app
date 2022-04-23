package com.webperside.deliveryapp.orderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI orderOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Order Service OPEN API")
                        .description("Order Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

//    private ApiKey apiKey() {
//        return new ApiKey("apiKey", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth())
//                .forPaths(PathSelectors.any()).build();
//    }

//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Collections.singletonList(new SecurityReference("apiKey", authorizationScopes));
//    }

//    private List<Parameter> globalParameterList() {
//        val authTokenHeader =
//                new ParameterBuilder()
//                        .name(ORGANIZATION_ID)
//                        .modelRef(new ModelRef("string"))
//                        .required(false)
//                        .parameterType("header")
//                        .description(ORGANIZATION_ID)
//                        .build();
//
//        return Collections.singletonList(authTokenHeader);
//    }
}
