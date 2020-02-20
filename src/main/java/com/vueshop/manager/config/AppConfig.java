package com.vueshop.manager.config;

import com.vueshop.manager.config.bean.SpringBeanUtil;
import com.vueshop.manager.dao.dto.AuthInfoDto;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 项目名称:com.examples.demo.mapper 描述: 创建人:ryw 创建时间:2018/12/25
 */
@Configuration
public class AppConfig {

	@Value("${threadPool.corePoolSize}")
	private int corePoolSize;

	@Value("${threadPool.keepAliveSeconds}")
	private int keepAliveSeconds;

	@Value("${threadPool.maxPoolSize}")
	private int maxPoolSize;

	@Value("${threadPool.queueCapacity}")
	private int queueCapacity;

	@Bean
	public SpringBeanUtil springBeanUtil() {
		return new SpringBeanUtil();
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setRejectedExecutionHandler(new CallerRunsPolicy());
		return executor;
	}

	@Bean
	public Map<String, AuthInfoDto> redisCache(){
		return new ConcurrentHashMap<>();
	}

}
