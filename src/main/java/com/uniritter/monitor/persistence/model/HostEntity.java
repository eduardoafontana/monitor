package com.uniritter.monitor.persistence.model;

public class HostEntity {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public int getMac() {
		return mac;
	}

	public void setMac(int mac) {
		this.mac = mac;
	}

	public long id;
	public int ip;
	public int mac;
	
	public HostEntity(){}
	
	public HostEntity(long id, int ip, int mac) {
		this.id = id;
		this.ip = ip;
		this.mac = mac;
	}
}
