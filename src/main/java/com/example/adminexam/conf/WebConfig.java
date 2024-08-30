package com.example.adminexam.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.adminexam.interceptor.Interceptor;

//@Configuration : 설정(인터셉터, 암호화, 외부 서버연동 등)부분을 하는 곳
@Configuration
@MapperScan(value= {"com.example.adminexam.mapper"})
public class WebConfig implements WebMvcConfigurer{
	 @Autowired
	 Interceptor interceptor;
	 @Override
	public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(interceptor) //인터셉터 추가
					.addPathPatterns("/emp","/emp/**","/dept","/dept/**", "/dept-all") //인터셉터할 URL
					.excludePathPatterns("/login"); //인터셉터제외 URL
	}
}
