package com.uniritter.monitor.domain.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.IHostRepository;

@Component
public class HostService {

	private final IHostRepository hostRepository;
	
	@Autowired
	public HostService(IHostRepository hostRepository){
		this.hostRepository = hostRepository;
	}
	
	public String[] getGrupos() {
		return Arrays.toString(HostGrupo.values()).split(", ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Host> getHosts(int metricaId) {
		
		//aplicar este gethosts dentro de um lazy load da metrica e remover do servico da metrica
		
		return (List<Host>)hostRepository.getListFromParent(metricaId);
	}		
}
