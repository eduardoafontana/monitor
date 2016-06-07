package com.uniritter.monitor.persistence.service;

public class NoRowsAffected extends Exception {

	public String message;

	public NoRowsAffected(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
