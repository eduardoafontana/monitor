package uniritter.monitor.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetricaService {
	
	@Autowired
	MetricaRepository repository;
	
	public List<Metrica> getMetricas() {
		return repository.getMetricas();
	}
	
	public Metrica createMetrica(String nomeMetrica){
		return repository.createMetrica(nomeMetrica);
	}
}
