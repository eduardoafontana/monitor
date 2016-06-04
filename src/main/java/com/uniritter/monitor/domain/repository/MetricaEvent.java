package com.uniritter.monitor.domain.repository;

import java.util.Observable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.domain.service.HostService;

@Component
public class MetricaEvent extends Observable {
	
	private IMetricaRepository metricaRepository;
	private IHostRepository hostRepository;
	
	@Autowired
	public void MetricaService(IMetricaRepository metricaRepository, IHostRepository hostRepository){
		this.metricaRepository = metricaRepository;
		this.hostRepository = hostRepository;
		
		this.addObserver(metricaRepository);
		this.addObserver(hostRepository);
	}
	
	public void save(Metrica metrica){
		ModelMapper modelMapper = new ModelMapper();
		MetricaEntityEvent metricaEntityEvent = modelMapper.map(metrica, MetricaEntityEvent.class);
		
		if(metrica.hosts != null){
			
		}
		
		//varre a metrica e manda para os hosts repositories salvar			
			
		setChanged();
		notifyObservers(metrica);
		
	}
}
