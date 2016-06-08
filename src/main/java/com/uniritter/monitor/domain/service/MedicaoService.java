package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.MedicaoEventData;
import com.uniritter.monitor.domain.repository.IMedicaoRepository;

@Component
public class MedicaoService {

	private final IMedicaoRepository medicaoRepository;

	@Autowired
	public MedicaoService(IMedicaoRepository medicaoRepository) {
		this.medicaoRepository = medicaoRepository;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medicao> retrieveAll(int metricaId) {
		List<MedicaoEventData> medicaoEventData = (List<MedicaoEventData>) medicaoRepository.getListFromRelation(metricaId);

		List<Medicao> medicaos = new ArrayList<Medicao>();

		for (MedicaoEventData medicaoData : medicaoEventData) {

			Medicao medicao = new Medicao(this);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(medicaoData, medicao);

			medicaos.add(medicao);
		}

		return medicaos;
	}

	public int create(int metricaId, MedicaoViewModel medicaoViewModel) {

		Medicao medicao = new Medicao(this);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(medicaoViewModel, medicao);

		return medicao.save(metricaId);
	}

	public int persist(MedicaoEventData medicaoEventData, int metricaId) {
		return medicaoRepository.saveToRelation(medicaoEventData, metricaId);
	}

	public int removePorMetrica(int metricaId) {
		return medicaoRepository.deleteFromRelation(metricaId);
	}
}
