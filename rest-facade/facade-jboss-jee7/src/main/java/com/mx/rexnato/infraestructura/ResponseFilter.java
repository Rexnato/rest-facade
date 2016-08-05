package com.mx.rexnato.infraestructura;

import java.io.IOException;
import java.util.List;

import javax.json.Json;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author Renato-PC
 * Funcionalidad intercepta todos las respuestas que da el servidor ,en este caso se aprovecha 
 * para agregar funcionalidad global en caso de servicios que retorner listas o respuestas nullas
 * agregarles el status 404 
 */
@Provider
public class ResponseFilter  implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		// TODO Auto-generated method stub
		//para el caso del core en caso de peticiones externas con ajax 
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		//para las busquedas en las cuales se retorne una lista vacia o un nullo , por si no aplican la excepcion de NoResultBussinesException
		Object entidad = responseContext.getEntity();
		boolean entidadVacia = false;
		if(entidad == null){
				entidadVacia = true;
		}else if(entidad instanceof List<?>){
			List<?> lista = (List<?>) entidad;
			if(lista.isEmpty()){
				entidadVacia = true;
			}
		}
	
		if(entidadVacia){
			responseContext.setEntity(Json.createObjectBuilder()
					.add("mensaje", "No se encontraron resultados")
					.add("status",Status.NOT_FOUND.getStatusCode())
					.build().toString()
					);
			responseContext.setStatus(Status.NOT_FOUND.getStatusCode());
		}
	}

	

}
