package com.uniritter.monitor.domain.model;

public class Host {
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
