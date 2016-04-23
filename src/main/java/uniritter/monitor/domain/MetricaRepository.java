package uniritter.monitor.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MetricaRepository {
	
	public List<Metrica> getMetricas() {
		
		List<Metrica> metricas = new ArrayList<Metrica>();
		
		//metricas.add(arg0);
		
		return metricas;
		
	}
	
	public Metrica createMetrica(String metrica){
		Metrica nova = new Metrica();//usar outro consutrutor
		
		return nova;
	}
}
