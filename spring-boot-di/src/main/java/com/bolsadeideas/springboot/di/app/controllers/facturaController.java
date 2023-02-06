package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.di.app.models.domain.Factura;



@Controller
@RequestMapping("/factura")//para darle una ruta base al controlador
public class facturaController {
	
	//vamos a inyectar en el controlador la factura
	
	@Autowired
	private Factura factura;
	
	@GetMapping("/ver")
	public String ver(Model model){
		
		//pasamos a la vista factura
		model.addAttribute("factura", factura);
		
		model.addAttribute("titulo", "ejemplo factura con inyeccion de dependencia");
		
		return "/factura/ver";
		
	}

}
