package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario") //mantenemos los datos del formulario para poder guardarlos despues o en BBDD como seria el identificador
public class FormController {
	
	@GetMapping("/form")// esta es la ruta del navegador donde estaran nuestros metodos de abajo
	
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Sandra");
		usuario.setUsername("einyd");
		usuario.setIdentificador("12.345.123-J");
		model.addAttribute("Titulo", "Formulario del usuario");
		model.addAttribute("usuario", usuario);
		return "form";
		
	}
	
	@PostMapping("/form") // este metodo es el que obtiene los datos del formulario y se mostraran en resultado
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
		model.addAttribute("Titulo", "Resultado del Formulario");
		
		if(result.hasErrors()) {
//			Map<String,String> errores = new HashMap<>();	
//			
//			result.getFieldErrors().forEach(err ->{
//				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//			});			
//			model.addAttribute("error", errores);
			return "form";
			
		}
		
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
		
	}

}
