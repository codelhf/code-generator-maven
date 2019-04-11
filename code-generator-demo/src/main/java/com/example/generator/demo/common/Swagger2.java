package com.example.generator.demo.common;

import io.swagger.models.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: Swagger2
 * @Description: Swagger2接口文档配置类
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-11 10:52:02
 */
@Configuration
//@EnableWebMvc //springMVC环境使用
@EnableSwagger2 //启用Swagger2
public class Swagger2 extends WebMvcConfigurationSupport {

    @Value("${swagger.ui.title:Swagger Apis}")
    private String title;
    @Value("${swagger.ui.version:Swagger Apis Version}")
    private String version;
    @Value("${swagger.ui.description:Swagger Apis Description}")
    private String description;
    //联系人
    @Value("${swagger.ui.contact.name:Swagger Apis Contact Name}")
    private String contactName;
    @Value("${swagger.ui.contact.email:Swagger Apis Contact Email}")
    private String contactEmail;
    @Value("${swagger.ui.contact.url:Swagger Apis Contact Url}")
    private String contactUrl;
    //许可人
    @Value("${swagger.ui.license:Swagger Apis License}")
    private String license;
    @Value("${swagger.ui.licenseUrl:Swagger Apis LicenseUrl}")
    private String licenseUrl;
    // 服务条款
    @Value("${swagger.ui.termsOfServiceUrl:Swagger Apis TermsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.exmaple.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .description(description)
                //联系人
                .contact(contactInfo())
                //许可人
//                .license(license)
//                .licenseUrl(licenseUrl)
                //服务条款URL
                .termsOfServiceUrl(termsOfServiceUrl)
                .build();
    }

    private String contactInfo() {
        return new Contact()
                .name(contactName)
                .email(contactEmail)
//                .url(contactUrl)
                .toString();
    }

    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
