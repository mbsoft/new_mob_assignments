package com.detroitlabs.skateboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Rohan Dongre", "", "dongrrohan421@gmail.com");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("SkateBoard Api Documentation", "In the shared mobility space, there are a number of different types of shared assets - cars, scooters, ebikes. For this project, we want to create a starting point for an API that will allow individual skateboarders to share their boards.\n" +
            "\n" +
            "The goal of this project is to assess development skills suitable for a backend developer position by testing knowledge and proficiency with software design, unit testing and web technologies like HTTP and REST.",
            "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
