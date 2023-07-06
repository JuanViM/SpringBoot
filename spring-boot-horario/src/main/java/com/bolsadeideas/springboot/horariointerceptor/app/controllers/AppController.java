package com.bolsadeideas.springboot.horariointerceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//esta clase sera el controlador

@Controller
public class AppController {

	@Value("${config.horario.apertura}") // con esto le damos a el valor apertura el valor que tiene esta property
	private Integer apertura;

	@Value("${config.horario.cierre}") // con esto le damos a el valor apertura el valor que tiene esta property
	private Integer cierre;
	
	@GetMapping({"/","/index"})//esta sera la ruta donde el controlador actuara
	public String index(Model model) {
		
		model.addAttribute("titulo", "Bienvenido al horario de atecion a clientes");
		return "index";
	}
	
	@GetMapping("/cerrado")
	public String cerrado(Model model) {
		
		StringBuilder mensaje = new StringBuilder("Cerrado, por favor visitenos desde las  ");
		mensaje.append(apertura);
		mensaje.append(" y las ");
		mensaje.append(cierre);
		mensaje.append(" hrs. Gracias. ");
		model.addAttribute("titulo","Fuera del horario de atencion");
		model.addAttribute("mensaje", mensaje);
		
		return "cerrado";
	}
}
