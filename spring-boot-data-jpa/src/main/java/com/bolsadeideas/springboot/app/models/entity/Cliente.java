package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity//se utiliza para definir una clase Java que representa un objeto que se puede almacenar en una base de datos
@Table(name="clientes")//con esto indicamos el nombre de la tabla de la BBDD
public class Cliente implements Serializable {

	@Id//esto indica que este atributo es la key primary
	@GeneratedValue(strategy = GenerationType.IDENTITY)//la estrategia con la que genera la llave la bbdd
	private Long id;
	
	@NotEmpty//hacemos que este campo sea requerido
	@Size(max = 100)
	private String nombre;
	@NotEmpty//hacemos que este campo sea requerido
	private String apellido;
	@NotEmpty//hacemos que este campo sea requerido
	@Email//validamos que tenga un email
	private String email;
	
	
	@NotNull//validamos que la fecha no sea nula
	@Column(name = "create_at")//con esto definiremos cual sera el nombre de la columna en la bbdd
	@Temporal(TemporalType.DATE) //esto lo que hace es indicar el formato en el que se va a guardar la fecha de java en la tabla de BBDD
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createAt;
	
//	@PrePersist//justo antes de insertar el cliente en la bbdd va a llamar a este metodo persist
//	public void prePersist() {
//		createAt= new Date();
//	}
	
	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
