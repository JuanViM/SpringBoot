package com.bolsadeideas.springboot.error.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.error.models.domain.Usuario;
//con este @service podemos inyectarla en el controlador y utilizarla con control de dependencias
@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;
	
	
	public UsuarioServiceImpl() {
		
		this.lista = new ArrayList<>();
		this.lista.add(new Usuario(1, "Andrez", "Guzman"));
		this.lista.add(new Usuario(2, "Juan", "Villegas"));
		this.lista.add(new Usuario(3, "Sandra", "Fernandez"));
		this.lista.add(new Usuario(4, "xema", "Errrandonea"));
		this.lista.add(new Usuario(5, "priest", "catalizador"));
		this.lista.add(new Usuario(6, "asdarel", "cazademonio"));
	}

	@Override
	public List<Usuario> listar() {
		
		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {

		Usuario resultado = null;
		
		for(Usuario u:this.lista) {
			if(u.getId().equals(id)) {
				resultado = u;
				break;
			}
		}
		
		return resultado;
	}

	@Override
	public Optional<Usuario> obtenerPorIdOptional(Integer id) {
		Usuario usuario = this.obtenerPorId(id);
		return Optional.ofNullable(usuario);
	}

}
