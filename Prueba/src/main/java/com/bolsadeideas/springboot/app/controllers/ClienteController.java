package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.ClienteServiceImpl;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(id);
		if(cliente == null) {
			flash.addFlashAttribute("error","El cliente no existe en la BBDD");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "detalle de cliente " + cliente.getNombre());
		return "ver";
	}
	
	//@Qualifier("clienteDao")//esto podemos ponerlo si tenemos mas de un repository para indicar cual es el que queremos usar
	@Autowired
	private IClienteService clienteService;
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	
	@RequestMapping("/form")
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		model.put("cliente",cliente);
		model.put("titulo", "Formulario");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String,Object> model,RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error","ClienteId no existe en la BBDD");
			}
		} else {
			flash.addFlashAttribute("error","ClienteId no puede ser 0");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
		
	}
	
	//con este metodo vamos a guardar el cliente del formulario en la bbdd
	@RequestMapping(value = "/form", method=RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) { //con este @valid hacemos que todas las validaciones surjan efecto
		//@ModelAttribute("cliente") con esto le indicamos el nombre del formulario
		// con BindingResult realizamos validaciones de datos en formularios
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "formulario del cliente");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("succes","Cliente Creado Correctamente");
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String emilinar(@PathVariable(value="id") Long id,RedirectAttributes flash) {
		
		if(id > 0 ) {
			clienteService.delete(id);
			flash.addFlashAttribute("succes","Cliente Eliminado");
		}
		return "redirect:/listar";
	}

}
