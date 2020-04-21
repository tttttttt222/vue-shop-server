package com.vueshop.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目名称:vue-shop-manager 描述: 创建人:ryw 创建时间:2020/3/2
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	AuthInterceptor authInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
				.addPathPatterns("/api/private/v1/**")
				.excludePathPatterns("/api/private/v1/login")
				.excludePathPatterns("/api/private/v1/historyBrief/**")
				.excludePathPatterns("/api/private/v1/historyBriefEvent/**");
	}


}
