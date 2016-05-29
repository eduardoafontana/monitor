package com.uniritter.monitor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import static org.junit.Assert.*;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.domain.model.MetricaDados;
import com.uniritter.monitor.persistence.model.MetricaEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMonitorApplication {
		
	@Test
	public void testMetricaMapper(){
		Metrica metrica = new Metrica(111, "aaa", new Date());
		
		ModelMapper modelMapper = new ModelMapper();
		MetricaEntity metricaEntity = modelMapper.map(metrica, MetricaEntity.class);
		
		assertNotNull(metricaEntity.id);
		assertNotNull(metricaEntity.nome);
		assertNotNull(metricaEntity.created);
	}
	
	@Test
	public void testMetricaMapperList(){	
		List<MetricaDados> metricaDadosList = new ArrayList<MetricaDados>();
		MetricaDados metricaDados = new MetricaDados();
		metricaDados.id = 14;
		metricaDados.nome = "1414";
		metricaDados.created = new Date();
		metricaDadosList.add(metricaDados);
		
		ModelMapper modelMapper = new ModelMapper();	
		Type listType = new TypeToken<List<MetricaEntity>>() {}.getType();
		List<MetricaEntity> metricaEntityList = modelMapper.map(metricaDadosList, listType);		
		
		assertTrue(metricaEntityList.size() > 0);
	}	
	
	@Test
	public void testMetricaMapperList2(){
		
		ArrayList<MetricaDados> metricaDadosList = new ArrayList<MetricaDados>();
		MetricaDados metricaDados = new MetricaDados();
		metricaDados.id = 14;
		metricaDados.nome = "1414";
		metricaDados.created = new Date();
		metricaDadosList.add(metricaDados);
		
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<ArrayList<MetricaEntity>>() {}.getType();
		ArrayList<MetricaEntity> metricaEntityList = modelMapper.map(metricaDadosList, listType);
		
		assertTrue(metricaEntityList.size() > 0);
	}
}
