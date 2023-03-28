package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("tiempoTranscurridoInterceptor") // con esto podemos inyectarlo
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {
	

	//que es esto mirarlo bien
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}
		HandlerMethod metodo = (HandlerMethod) handler;
		if(handler instanceof HandlerMethod) {
			logger.info("es un metodo del controlador: "+ metodo.getMethod().getName());
		}
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando.....");
		
		long tiempoInicio = System.currentTimeMillis(); //calcula el tiempo que tarda en iniciar
		
		request.setAttribute("tiempoInicio", tiempoInicio); // lo guardamos en el request???
		
		Random random = new Random();
		
		Integer demora = random.nextInt(100);
		
		Thread.sleep(demora); // simulamos un delay
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("post")) {
		
		}
		
		long tiempoFin = System.currentTimeMillis(); //calculamos el tiempo que ha tardado en acabar
		long tiempoInicio = (Long) request.getAttribute("tiempoInicio"); //que hace esto lo obtenemos a traves del request
		
		long tiempoTranscurrido = tiempoFin- tiempoInicio; 
		
		if(modelAndView != null) {
			modelAndView.addObject("tiempoTranscurrido",tiempoTranscurrido);
		}
		
		logger.info("TiempoTranscurrido: " + tiempoTranscurrido + " en milisegundos");
		logger.info("TiempoTranscurridoInterceptor: postHandle() Saliendo.....");
		}
	
	

}
