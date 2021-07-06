package me.gogosing.config;

import static springfox.documentation.builders.RequestHandlerSelectors.any;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"!prod"})
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api() {
    List<ResponseMessage> responseMessageList = Arrays.asList(
        new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).message("잘못된 요청").build(),
        new ResponseMessageBuilder().code(HttpStatus.UNAUTHORIZED.value()).message("인증 실패").build(),
        new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("API 오류").build()
    );

    final String stringModelType = "string";
    final String headerParameterType = "header";
    List<Parameter> globalOperationParameters = Arrays.asList(
        new ParameterBuilder()
            .name("X-Location-Pathname")
            .description("X-Location-Pathname Header-swagger내 호출시 불필요")
            .required(true)
            .modelRef(new ModelRef(stringModelType))
            .parameterType(headerParameterType)
            .defaultValue("/")
            .build(),
        new ParameterBuilder()
            .name("X-Requested-With")
            .description("AJAX Header")
            .required(true)
            .modelRef(new ModelRef(stringModelType))
            .parameterType(headerParameterType)
            .defaultValue("XMLHttpRequest")
            .build()
    );

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .globalOperationParameters(globalOperationParameters)
        .globalResponseMessage(RequestMethod.GET, responseMessageList)
        .globalResponseMessage(RequestMethod.POST, responseMessageList)
        .globalResponseMessage(RequestMethod.PUT, responseMessageList)
        .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
        .select()
        .apis(any())
        .paths(PathSelectors.any())
        .build();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("SAMPLE REST API 명세서")
        .description("SAMPLE REST API 명세서입니다.")
        .termsOfServiceUrl("gogosing@mz.co.kr")
        .contact(new Contact("메가존", "https://mz.co.kr", "gogosing@mz.co.kr"))
        .version("1.0")
        .build();
  }
}
