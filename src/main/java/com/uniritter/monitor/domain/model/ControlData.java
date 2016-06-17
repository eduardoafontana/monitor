package com.uniritter.monitor.domain.model;

import java.util.Date;

public class ControlData {

	protected int id;
	protected Date created;
	
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
