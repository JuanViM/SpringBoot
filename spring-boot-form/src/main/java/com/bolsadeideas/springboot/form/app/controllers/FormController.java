package com.bolsadeideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario") // mantenemos los datos del formulario para poder guardarlos despues o en BBDD
								// como seria el identificador
public class FormController {

	@Autowired
	private UsuarioValidador validador;
	
	//este sera el atributo de los paises
	
	@Autowired
	private PaisService paisService; 
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		dateformat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateformat, true)); // esta
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellidos", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisEditor); // asi metemos un objeto creado como pais
		binder.registerCustomEditor(Role.class, "roles", roleEditor); // asi metemos un objeto creado como pais
		
	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre","Mujer");
	}
	
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.roleService.listar();
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {

		return paisService.listar();
	}

	@ModelAttribute("paises")
	public List<String> paises() {

		return Arrays.asList("España", "Mexico", "Chile", "Uruguay");
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();
		paises.put("ES", "ESPAÑA");
		paises.put("MX", "MEXICO");
		paises.put("CL", "CHILE");
		paises.put("UR", "URUGUAY");

		return paises;

	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		
		return roles;

	}

	@GetMapping("/form") // esta es la ruta del navegador donde estaran nuestros metodos de abajo
	public String form(Model model) {

		Usuario usuario = new Usuario();

		usuario.setNombre("Sandra");
		usuario.setUsername("einyd");
		usuario.setIdentificador("12.345.123-J");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Algun valor secreto *****");
		usuario.setRoles(Arrays.asList(new Role(2,"Usuario","ROLE_USER")));
		usuario.setPais(new Pais(1, "ES", "España"));// ponemos españa como pais por defecto
		model.addAttribute("Titulo", "Formulario del usuario");
		model.addAttribute("usuario", usuario);
		return "form";

	}

	@PostMapping("/form") // este metodo es el que obtiene los datos del formulario y se mostraran en
							// resultado
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {

		// validador.validate(usuario, result);
		

		if (result.hasErrors()) {
			model.addAttribute("Titulo", "Resultado del Formulario");
//			Map<String,String> errores = new HashMap<>();	
//			
//			result.getFieldErrors().forEach(err ->{
//				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//			});			
//			model.addAttribute("error", errores);
			return "form";

		}

		
		return "redirect:/ver";

	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "usuario",required = false) Usuario usuario,Model model, SessionStatus status) {
		if(usuario == null ) {
			return "redirect:/form";
		}
		
		model.addAttribute("Titulo", "Resultado del Formulario");
		status.setComplete();
		return "resultado";
	}
	

}
