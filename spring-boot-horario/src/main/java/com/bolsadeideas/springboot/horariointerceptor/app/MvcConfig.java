package com.bolsadeideas.springboot.horariointerceptor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Autowired
	@Qualifier("horario") //le damos el mismo nombre que le dimos en HorarioInterceptor
	private HandlerInterceptor horario;
	
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(horario).excludePathPatterns("/cerrado"); //con esto escluimos para que no lo llame varias veces y pegue un error
	}
	

}
