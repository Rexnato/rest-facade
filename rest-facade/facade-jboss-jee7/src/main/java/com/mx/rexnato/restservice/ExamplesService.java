package com.mx.rexnato.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mx.rexnato.exception.BusinessException;

@Path("/examples")
public class ExamplesService {
		
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hola-mundo")
	public Response holaMundo(){
		System.out.println("responder hola mundo");
		return Response.ok("holaMundo").build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hola-mundo")
	public Response holaMundoPost(){
		System.out.println("responder hola mundoPost");
		return Response.ok("holaMundo").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista-numeros/{cantidad}")
	public Response listaNumeros(@PathParam("cantidad")int cantidad){
		List<Integer> numeros = new ArrayList<Integer>();
		if(cantidad == 0){
			//si mandas el 0 y quieres ver el 204 recomiendo cheques tu consola en tu navegador
			return Response.ok(numeros).build();
		}
		for (int x=1;x<=cantidad;x++){
			numeros.add(x);
		}
		   
		return Response.ok(numeros).build();
	}
	
	/***
	 * Para demostrar el uso de la excepcion controlada y como lo cacha JAXRS con el RestExceptionMapper
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/juego-mortal")
	public Response exceptionControlada(){
		boolean generarExcepcion = new Random().nextBoolean();
		if(generarExcepcion){
			throw new BusinessException("Lo sentimos te toco perder muaaajajajajaja");//verifica en tu navegador como obtienes un 202 en este caso 
		}
		return Response.ok("Ganasteee :D oppa gangnam style ehhh :D(Pd :imagina es una cancion para que disfrutes tu triunfo) ").build();
	}
}
