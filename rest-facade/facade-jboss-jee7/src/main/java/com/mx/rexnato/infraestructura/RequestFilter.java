package com.mx.rexnato.infraestructura;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
/**
 * 
 * @author Renato 
 * Aqui se puede implementar la seguridad inicial 
 * posteriormente se agrega una version con JWT 
 *
 */
@Provider
public class RequestFilter implements ContainerRequestFilter{

	@Context
	UriInfo uriInfo;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		// TODO Auto-generated method stub
		System.out.println("en el request filter"+requestContext.getMethod());
		//requestContext.getHeaders(); si quieres buscar un token en el encabezadado
		//acceder a los path param
		MultivaluedMap<String, String> pathParam= uriInfo.getPathParameters();
		System.out.println("cabtidad path param"+pathParam.size());
		///
	}

}
