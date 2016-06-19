package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.common.InvalidType;
import com.uniritter.monitor.domain.client.model.HostClientModel;
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.IHostRepository;
import com.uniritter.monitor.domain.repository.model.HostEventData;

@Component
public class HostService {

	private final IHostRepository hostRepository;

	@Autowired
	public HostService(IHostRepository hostRepository) {
		this.hostRepository = hostRepository;
	}

	public String[] getGrupos() {
		return Arrays.toString(HostGrupo.values()).split(", ");
	}

	public List<Host> getTodos(int metricaId) {
		List<HostEventData> hostEventData = hostRepository.<HostEventData>getListFromRelation(metricaId);

		List<Host> hosts = new ArrayList<Host>();

		for (HostEventData hostData : hostEventData) {

			Host host = new Host(this);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(hostData, host);

			hosts.add(host);
		}

		return hosts;
	}

	public int criar(int metricaId, HostClientModel hostViewModel) {

		Host host = new Host(this);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(hostViewModel, host);

		return host.save(metricaId);
	}

	public int gravar(HostEventData hostEventData, int metricaId) {
		return hostRepository.saveToRelation(hostEventData, metricaId);
	}

	public int apagarPorMetrica(int metricaId) {
		return hostRepository.deleteFromRelation(metricaId);
	}

	public HostGrupo verificarGrupo(String grupo) throws InvalidType {
		
		try {
			
			return HostGrupo.valueOf(grupo);
		} catch (IllegalArgumentException e) {
			
			throw new InvalidType("Valor grupo: " + grupo + " invalido!");
		}
	}
}
