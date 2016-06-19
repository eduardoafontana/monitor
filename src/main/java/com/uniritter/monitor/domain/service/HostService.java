package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public String[] retrieveGrupos() {
		return Arrays.toString(HostGrupo.values()).split(", ");
	}

	public List<Host> retrieveAll(int metricaId) {
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

	public int create(int metricaId, HostClientModel hostViewModel) {

		Host host = new Host(this);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(hostViewModel, host);

		return host.save(metricaId);
	}

	public int persist(HostEventData hostEventData, int metricaId) {
		return hostRepository.saveToRelation(hostEventData, metricaId);
	}

	public int removePorMetrica(int metricaId) {
		return hostRepository.deleteFromRelation(metricaId);
	}
}
