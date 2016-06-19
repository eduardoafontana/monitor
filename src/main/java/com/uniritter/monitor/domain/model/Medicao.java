package com.uniritter.monitor.domain.model;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.model.MedicaoEventData;
import com.uniritter.monitor.domain.service.MedicaoService;

@Component
public class Medicao extends ControlData {
	
	private int mac;
	private int valor;
	private Date quando;
	
	private MedicaoService medicaoService;

	@Autowired
	public Medicao(MedicaoService medicaoService) {
		this.medicaoService = medicaoService;
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
	
	public int save(int metricaId) {

		//TODO: local para aplicar regras de negocio antes de salvar. Ex: validacao

		ModelMapper modelMapper = new ModelMapper();
		MedicaoEventData medicaoData = modelMapper.map(this, MedicaoEventData.class);

		this.id = medicaoService.persist(medicaoData, metricaId);

		return this.id;
	}
}
