package com.chunjae.jpa1.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// api 테스트를 하기 위한 것
@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) // 문서화 true, 문서화 안함 false
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chunjae.jpa1.ctrl")) // api 요청자 지정
                .paths(PathSelectors.any()) // 컨트롤러 내부의 모든 것에 대해서 사용
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("Spring Boot Shop Project Swagger")
                        .build());
    }

}
