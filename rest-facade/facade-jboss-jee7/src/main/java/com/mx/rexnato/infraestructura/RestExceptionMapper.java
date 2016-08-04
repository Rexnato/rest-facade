package com.mx.rexnato.infraestructura;

import javax.json.Json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;



import javax.ws.rs.ext.Provider;

import com.mx.rexnato.exception.BusinessException;


/***
 * 
 * @author Renato
 * Sirve para interceptar todas las excepciones generadas en el aplicativo 
 * puede programarse un comportamiento para cada uno de ellos
 *
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		// TODO Auto-generated method stub
		//excepciones controlads y relanzadas por bussines exception lanza un mensaje con status 202 , indicando un error de negocio
		if(exception.getClass().equals(BusinessException.class)){
			return Response.status(Status.ACCEPTED).type(MediaType.APPLICATION_JSON).entity(
						Json.createObjectBuilder().
							add("mensaje", exception.getMessage()).
							build().toString()
					).build();
		}else{
			exception.printStackTrace();
			//para todo aquello no gestionado ojo que si enmascara todo eh es mejor utilizarlo en produccion una caja negra XD jaja
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(
					Json.createObjectBuilder()
						.add("mensaje", "Ocurrio un error no controlado")
						.add("localized",exception.getLocalizedMessage())
						.build().toString()
			).build();
		}
	}



}
