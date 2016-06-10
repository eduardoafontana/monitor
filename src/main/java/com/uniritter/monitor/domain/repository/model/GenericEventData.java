package com.uniritter.monitor.domain.repository.model;

import java.util.Date;

public abstract class GenericEventData {

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
