package com.mx.rexnato.infraestructura;

import java.io.IOException;
import java.util.List;

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
 * agregarles el status 204 No Content
 */
@Provider
public class ResponseFilter  implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		// TODO Auto-generated method stub
		
		Object entidad = responseContext.getEntity();
			
		if(entidad == null){
			responseContext.setStatus(Status.NO_CONTENT.getStatusCode());
			return;
		}else if(entidad instanceof List<?>){
			List<?> lista = (List<?>) entidad;
			if(lista.isEmpty()){
				responseContext.setStatus(Status.NO_CONTENT.getStatusCode());
				return;
			}
		}
		//para el caso del core en caso de peticiones externas con ajax 
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		
	}

	

}
