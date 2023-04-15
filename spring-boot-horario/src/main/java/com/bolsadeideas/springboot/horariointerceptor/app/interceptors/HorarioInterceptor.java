package com.bolsadeideas.springboot.horariointerceptor.app.interceptors;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("horario") // para convertirlo en un componente en spring y poder inyectarlo, tambien le damos un nombre para luego poder injectarlo
public class HorarioInterceptor implements HandlerInterceptor {

	@Value("${config.horario.apertura}") // con esto le damos a el valor apertura el valor que tiene esta property
	private Integer apertura;

	@Value("${config.horario.cierre}") // con esto le damos a el valor apertura el valor que tiene esta property
	private Integer cierre;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	
		Calendar calendar = Calendar.getInstance(); 
		//obtenemos la hora actual
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		
		if(hora >= apertura && hora < cierre) {
			
			StringBuilder mensaje = new StringBuilder("Bienvenido al horario de atencion a clientes");
			mensaje.append(", atendemos desde las ");
			mensaje.append(apertura);
			mensaje.append("hrs. ");
			mensaje.append("hasta las ");
			mensaje.append(cierre);
			mensaje.append("Gracias por su visita.");
			//pasaremos el mensaje a los atributos del request
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}

		//ahora haremos el false osea que entre y no sea en horario de apertura
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//guardamos en un String el mensaje del request
		String mensaje = (String) request.getAttribute("mensaje");
		if(modelAndView != null) {
			//pasamos el mensaje a la vista
			modelAndView.addObject("horario", mensaje);
		}

		
	}

}
