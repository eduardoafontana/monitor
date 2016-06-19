package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.common.NoResultFound;
import com.uniritter.monitor.domain.client.model.MedicaoClientModel;
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
	
	public List<Medicao> getTodos(int metricaId) {
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

	public int criar(int metricaId, MedicaoClientModel medicaoViewModel, MetricaService metricaService) throws NoResultFound {

		Medicao medicao = new Medicao(this);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(medicaoViewModel, medicao);
		
		int id = medicao.save(metricaId);
		
		Metrica metrica = metricaService.getUnico(metricaId);
		metrica.verificarAlertasNotificar();
		
		return id;
	}

	public int gravar(MedicaoEventData medicaoEventData, int metricaId) {
		return medicaoRepository.saveToRelation(medicaoEventData, metricaId);
	}

	public int apagarPorMetrica(int metricaId) {
		return medicaoRepository.deleteFromRelation(metricaId);
	}
}
