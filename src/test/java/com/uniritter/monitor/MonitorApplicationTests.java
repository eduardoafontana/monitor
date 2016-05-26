package com.uniritter.monitor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.domain.model.MetricaDados;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.service.MetricaService;
import com.uniritter.monitor.persistence.model.MetricaEntity;
import com.uniritter.monitor.persistence.repository.MetricaRepository;
import com.uniritter.monitor.persistence.service.MetricaDao;
import com.uniritter.monitor.persistence.service.MetricaRowMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class MonitorApplicationTests {
	
	//http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html

	@Autowired
	public JdbcTemplate jdbcTemplate;    

	@Autowired
	public MetricaDao metricaDao;    	

	@Autowired
	public MetricaService metricaService;

	@Autowired
	public MetricaRepository metricaRepository;
	
	@Test
	public void testMetricaService() {
		
		List<Metrica> metricas = metricaService.getMetricas();
		
		MetricaDados metricaDados = new MetricaDados();
		metricaDados.id = 999;
		metricaDados.nome = "metrica de teste de repositorio";
		metricaDados.created = new Date();
		
		metricaService.createMetrica(metricaDados);
		
		assertTrue(metricaService.getMetricas().size() > metricas.size());
		assertTrue((metricaService.getMetricas().size() - 1) == metricas.size());
	}
	
	@Test 
	public void testMetricaRepositoryGetList(){
		List<? extends IEntity> metricas = metricaRepository.getList();
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
	
	@Test
	public void testMetricaRepositorySave() {
		Metrica metrica = new Metrica(888, "metrica de teste de repositorio", new Date());		
		
		assertEquals(1, metricaRepository.save(metrica));
	}
	
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
	
	@Test
	public void testJdbcInsideDao() {
		
		assertTrue(metricaDao.isJdbcNotNull());
	}		
	
	@Test
	public void testMetricaDaoGetMetricas() {
		
		List<MetricaEntity> metricas = metricaDao.getMetricas();
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
	
	@Test
	public void testMetricaDaoCreateMetrica() {
		MetricaEntity metrica = new MetricaEntity(777, "metrica de teste de repositorio", new Date());		
		
		assertEquals(1, metricaDao.createMetrica(metrica));
	}
	
	@Test
	public void testDataBase(){
		assertNotNull(jdbcTemplate);
		
		List<MetricaEntity> metricaEntity = this.jdbcTemplate.query("select * from metrica order by id", new MetricaRowMapper());
		
		assertNotNull(metricaEntity);
	}
}
