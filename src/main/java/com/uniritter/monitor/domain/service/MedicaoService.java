package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.client.model.MedicaoViewModel;
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.IMedicaoRepository;
import com.uniritter.monitor.domain.repository.model.MedicaoEventData;

@Component
public class MedicaoService {

	private final IMedicaoRepository medicaoRepository;

	@Autowired
	public MedicaoService(IMedicaoRepository medicaoRepository) {
		this.medicaoRepository = medicaoRepository;
	}
	
	public List<Medicao> retrieveAll(int metricaId) {
		List<MedicaoEventData> medicaoEventData = medicaoRepository.<MedicaoEventData>getListFromRelation(metricaId);

		List<Medicao> medicaos = new ArrayList<Medicao>();

		for (MedicaoEventData medicaoData : medicaoEventData) {

			Medicao medicao = new Medicao(this);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(medicaoData, medicao);

			medicaos.add(medicao);
		}

		return medicaos;
	}

	public int create(int metricaId, MedicaoViewModel medicaoViewModel, MetricaService metricaService) {

		Medicao medicao = new Medicao(this);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(medicaoViewModel, medicao);
		
		int id = medicao.save(metricaId);
		
		Metrica metrica = metricaService.retrieve(metricaId);
		metrica.verificarAlertasNotificar();
		
		return id;
	}

	public int persist(MedicaoEventData medicaoEventData, int metricaId) {
		return medicaoRepository.saveToRelation(medicaoEventData, metricaId);
	}

	public int removePorMetrica(int metricaId) {
		return medicaoRepository.deleteFromRelation(metricaId);
	}
}
