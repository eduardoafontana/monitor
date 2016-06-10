package com.uniritter.monitor.persistence.model;

import java.util.Date;

public abstract class GenericEntity {
	
	private int id;
	private Date created;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
