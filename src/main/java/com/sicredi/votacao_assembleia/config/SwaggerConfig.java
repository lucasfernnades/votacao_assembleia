package com.sicredi.votacao_assembleia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String API_PACKAGE = "com.sicredi.votacao_assembleia";

    @Bean
    public Docket assemblyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage(API_PACKAGE))
                .build()
                .apiInfo(apiInfo("1.0"));
    }

    private ApiInfo apiInfo(String version) {
        return new ApiInfoBuilder().title("Votação Assembleia")
                .description("Votação Assembleia")
                .version(version)
                .build();
    }
}