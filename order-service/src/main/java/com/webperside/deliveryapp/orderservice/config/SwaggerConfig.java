package com.webperside.deliveryapp.orderservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI orderOpenAPI() {
        return new OpenAPI()
//                .components(new Components()
//                        .addHeaders("lang", new Header().description("Language of content, by default is en").schema(new StringSchema())))
//                .components(new Components()
//                        .addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
//                        .addParameters("myHeader1", new Parameter().in("header").schema(new StringSchema()).name("myHeader1"))
//                        .addHeaders("myHeader2", new Header().description("myHeader2 header").schema(new StringSchema())))
                .info(new Info().title("Order Service API")
                        .description("Order Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public OperationCustomizer customize() {
        return (operation, handlerMethod) -> operation.addParametersItem(
                new Parameter()
                        .in("header")
                        .required(true)
                        .description("Language of content, by default is en")
                        .name("lang")
        );
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
