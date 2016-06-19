package com.uniritter.monitor.persistence.service;

@SuppressWarnings("serial")
public class NoRowsAffected extends RuntimeException {
	
	public NoRowsAffected(String message) {
		
		super(message);
	}
}
