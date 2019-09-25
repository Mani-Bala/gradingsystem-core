package com.revature.gradingsystem.util;

public class Logger {

	private Logger() {
		
	}
	
	private static final Logger LOGGER = null;
	
	public static Logger getInstance() {
		if(LOGGER == null) {
			return new Logger();
		}
		return LOGGER;
	}
	
	public static final String LEVEL = "";
	
	public void debug(String message) {
		if( LEVEL .equals("DEBUG") )
			System.out.println(message);
	}
	
	public void error(String message) {
		if( LEVEL.equals("ERROR") )
			System.out.println(message);
	}
	
	public void error(Exception e) {
		if( LEVEL.equals("ERROR") )
			System.out.println(e);
	}
	
	public void info(String message) {
		if( LEVEL.equals("INFO") )
			System.out.println(message);
	}
	
	public void print(String message) {
		if( LEVEL.equals("PRINT"))
			System.out.println(message);
	}
}
