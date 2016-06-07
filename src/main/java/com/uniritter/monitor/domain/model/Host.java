package com.uniritter.monitor.domain.model;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.HostEventData;
import com.uniritter.monitor.domain.service.HostService;

@Component
public class Host {

	private int id;
	private int ip;
	private int mac;
	private HostGrupo grupo;
	private Date created;

	private HostService hostService;

	@Autowired
	public Host(HostService hostService) {
		this.hostService = hostService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public HostGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(HostGrupo grupo) {
		this.grupo = grupo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int save(int metricaId) {
		// testar se host eh valido e outras regras de negocio

		ModelMapper modelMapper = new ModelMapper();
		HostEventData hostData = modelMapper.map(this, HostEventData.class);

		this.id = hostService.persist(hostData, metricaId);

		return this.id;
	}
}
