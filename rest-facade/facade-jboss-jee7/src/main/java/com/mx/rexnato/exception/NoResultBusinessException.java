package com.mx.rexnato.exception;

import java.io.Serializable;
/***
 * Excepcion utilizable en el caso de las busquedas que impida que se retorne un resultado 
 * @author Renato-PC
 *
 */
public class NoResultBusinessException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoResultBusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoResultBusinessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoResultBusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoResultBusinessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoResultBusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
