package com.bolsadeideas.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.service.IServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicioComplejo;

@Configuration
public class AppConfig {
	
	@Bean("MiServiciosimple")
	@Primary
	 IServicio RegistrarMiServicioSimple() {
		return new MiServicio();
	}

    @Bean("MiServicioComplejo")
    
    IServicio RegistrarMiServicioComplejo() {
        return new MiServicioComplejo();
    }
    
    @Bean("ItemsFactura")//Unicamente es necesario el nombre en caso de que tengamos mas de un Bean de tipo List<ItemFactura>
	@Primary
    public List<ItemFactura> registrarItems(){
		
		Producto producto = new Producto("Camara", 100);
		Producto producto1 = new Producto("Bicicleta",300);
		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto, 3);
		return Arrays.asList(linea1,linea2); // esto es lo mismo que crearlo y agregarlos
		
	}
    
    @Bean("ItemsFacturaOficina")//Unicamente es necesario el nombre en caso de que tengamos mas de un Bean de tipo List<ItemFactura>
 	public List<ItemFactura> registrarItemsOficina(){
 		
 		Producto producto = new Producto("Grafica", 500);
 		Producto producto1 = new Producto("Monitor",250);
 		Producto producto2 = new Producto("PC",1250);
 		Producto producto3 = new Producto("Teclado",20);
 		Producto producto4 = new Producto("Raton",17);
 		Producto producto5 = new Producto("Cascos",12);
 		ItemFactura linea = new ItemFactura(producto, 2);
 		ItemFactura linea1 = new ItemFactura(producto1, 5);
 		ItemFactura linea2 = new ItemFactura(producto2, 5);
 		ItemFactura linea3 = new ItemFactura(producto3, 5);
 		ItemFactura linea4 = new ItemFactura(producto4, 5);
 		ItemFactura linea5 = new ItemFactura(producto5, 5);
 		return Arrays.asList(linea,linea1,linea2,linea3,linea4,linea5); // esto es lo mismo que crearlo y agregarlos
 		
 	}

}
