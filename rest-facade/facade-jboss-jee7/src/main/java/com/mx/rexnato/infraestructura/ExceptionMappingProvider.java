package com.mx.rexnato.infraestructura;

import javax.json.Json;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;



import javax.ws.rs.ext.Provider;

import com.mx.rexnato.exception.BusinessException;
import com.mx.rexnato.exception.NoResultBusinessException;


/***
 * 
 * @author Renato
 * Sirve para interceptar todas las excepciones generadas en el aplicativo 
 * puede programarse un comportamiento para cada uno de ellos
 *
 */
@Provider
public class ExceptionMappingProvider implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		// TODO Auto-generated method stub
		//inicial excepcion no contemplada retornara un 503 por elegancia jeje 
		int status  = 500;
		String mensaje = "El servidor encontró una condición inesperada que le impidió cumplir con la solicitud";
		
		//Para excepciones de negocio
		if(exception instanceof BusinessException){
			status = 418; //I am teapot http://www.josevazquez.net/codigo-de-estado-http-418-i%C2%B4m-a-teapot/
			mensaje = exception.getMessage();
		}else if(exception instanceof NoResultBusinessException){
			status = Status.NOT_FOUND.getStatusCode();
			mensaje = exception.getMessage();
		}else if (exception instanceof NotFoundException){
			status = Status.NOT_FOUND.getStatusCode();
			mensaje = "El recurso al cual desea acceder no existe, ¡Verifique su path!";
		}
		
		return Response.status(status).type(MediaType.APPLICATION_JSON).entity(
				Json.createObjectBuilder()
					.add("mensaje", mensaje)
					.add("status",status)
					.build().toString()
		).build();
	}



}
