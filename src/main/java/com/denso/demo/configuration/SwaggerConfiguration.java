package com.denso.demo.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/skate-board/**"))
                .build()
                .genericModelSubstitutes(Optional.class)
                .globalResponseMessage(GET, getHttpResponseMessages())
                .globalResponseMessage(POST, getHttpResponseMessages())
                .globalResponseMessage(PUT, getHttpResponseMessages())
                .globalResponseMessage(DELETE, getHttpResponseMessages());
    }

    private List<ResponseMessage> getHttpResponseMessages() {
        return Lists.newArrayList(
                new ResponseMessageBuilder().code(NOT_FOUND.value()).message("Not Found").build(),
                new ResponseMessageBuilder().code(BAD_REQUEST.value()).message("Bad Request").build(),
                new ResponseMessageBuilder().code(INTERNAL_SERVER_ERROR.value()).message("Backend Error").build()
        );
    }
}
