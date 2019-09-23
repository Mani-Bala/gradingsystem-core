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
	
	public static final String level = "";
	
	public void debug(String message) {
		if( level == "DEBUG" )
			System.out.println(message);
	}
	
	public void error(String message) {
		if( level == "ERROR" )
			System.out.println(message);
	}
	
	public void error(Exception e) {
		if( level == "ERROR" )
			System.out.println(e);
	}
	
	public void info(String message) {
		if( level == "INFO" )
			System.out.println(message);
	}
	
	public void print(String message) {
		if( level == "PRINT" )
			System.out.println(message);
	}
}
