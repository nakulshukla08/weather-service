package com.sapient.weather.exception;

public class ServiceException extends Exception {
	
	public ServiceException(Throwable t, String message)
	{
		super(message, t);
	}

}
