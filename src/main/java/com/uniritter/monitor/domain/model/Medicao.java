package com.uniritter.monitor.domain.model;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.model.MedicaoEventData;
import com.uniritter.monitor.domain.service.MedicaoService;

@Component
public class Medicao {
	
	private int id;
	private int mac;
	private int valor;
	private Date quando;
	private Date created;
	
	private MedicaoService medicaoService;

	@Autowired
	public Medicao(MedicaoService medicaoService) {
		this.medicaoService = medicaoService;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMac() {
		return mac;
	}
	public void setMac(int mac) {
		this.mac = mac;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Date getQuando() {		
		return quando;
	}
	public void setQuando(Date quando) {
		this.quando = quando;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public int save(int metricaId) {
		// testar se medicao eh valido e outras regras de negocio

		ModelMapper modelMapper = new ModelMapper();
		MedicaoEventData medicaoData = modelMapper.map(this, MedicaoEventData.class);

		this.id = medicaoService.persist(medicaoData, metricaId);

		return this.id;
	}
}
