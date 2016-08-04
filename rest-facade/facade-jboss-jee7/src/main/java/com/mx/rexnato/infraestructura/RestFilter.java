package com.mx.rexnato.infraestructura;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/***
 * 
 * @author Renato-PC
 * Filtro agregado para el caso de los rest para que funcione con el cors 
 */
//@WebFilter(urlPatterns="/rest/*" , filterName="RestFilter")
public class RestFilter  implements Filter{
	
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq       = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) res;
		/**** PARA EL CASO DEL CORS ****/
		//Para el cors 
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
	    httpResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization,");
	    httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
	    httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	    
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
