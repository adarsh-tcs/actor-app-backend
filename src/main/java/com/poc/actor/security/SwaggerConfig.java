//package com.poc.actor.security;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
////@ConfigurationProperties("app.api")
////@ConditionalOnProperty(name="app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
//public class SwaggerConfig {
//
//	private String version = "1.0.0";
//	private String title = "Actor-Movies";
//	private String description = "Rest API for Actor Movie POC";
//	private String basePackage = "com.poc.actor";
//	private String contactName = "Adarsh Kumar";
//	private String contactEmail = "contactEmail.email.com";
//	private String apiPath = "/v1/api/public/.*";
//	
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//			.select()
//			.apis(RequestHandlerSelectors.basePackage(basePackage))
//			.paths(PathSelectors.regex(apiPath))
//			.build()
//			.directModelSubstitute(LocalDate.class, java.sql.Date.class)
//			.directModelSubstitute(LocalDateTime.class, java.util.Date.class)
//			.apiInfo(apiInfo());
//	}
//	
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//			.title(title)
//			.description(description)
//			.version(version)
//			.contact(new Contact(contactName, null, contactEmail))
//			.build();
//	}
//
//}
