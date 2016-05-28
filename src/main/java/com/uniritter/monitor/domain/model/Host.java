package com.uniritter.monitor.domain.model;

import com.uniritter.monitor.domain.repository.IEntity;

public class Host implements IEntity {
	public int IP;
	public int MAC;
	private HostGrupo grupo;
	
	public HostGrupo getGrupo() {
		return grupo;
	}

	public Host(HostGrupo grupo){
		this.grupo = grupo;
	}
}
