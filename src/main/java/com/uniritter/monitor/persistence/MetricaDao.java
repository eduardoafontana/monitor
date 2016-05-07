package com.uniritter.monitor.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Metrica;

@Component
public class MetricaDao {
	
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MetricaDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	public Metrica getMetrica(Long id){
//		return this.jdbcTemplate.queryForObject("select * from metrica where id = ?", Metrica.class, id);
//	}

	public List<Metrica> getMetricas(){
		//aqui no dao deve ser feito o map do objeto de dados (rowmap) a definicao de modelo de dados esperda pelo repositorio  
		return this.jdbcTemplate.query("select * from metrica order by id", new MetricaRowMapper());
	}
	
	public int createMetrica(Metrica metrica){
		//nao enviar metrica e sim metrica para dao, modelo de dados
		return jdbcTemplate.update("insert into metrica (nome, created) values (?, ?)", metrica.nome, metrica.created);
	}
}
