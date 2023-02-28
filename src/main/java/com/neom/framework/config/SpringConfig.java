package com.neom.framework.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.neom.framework.constants.FrameworkConstants;
import com.neom.framework.util.JwtFilter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

	@Bean
	Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(FrameworkConstants.BASE_PACKAGE))
				.paths(PathSelectors.regex(FrameworkConstants.REG_EX)).build();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new JwtFilter());
		filter.addUrlPatterns("/api/v1/departments");
		return filter;
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
