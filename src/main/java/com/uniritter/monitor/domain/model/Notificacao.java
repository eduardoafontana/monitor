package com.uniritter.monitor.domain.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.model.NotificacaoEventData;
import com.uniritter.monitor.domain.service.NotificacaoService;

@Component
public class Notificacao extends ControlData {

	private String mensagem;

	private NotificacaoService notificacaoService;

	@Autowired
	public Notificacao(NotificacaoService notificacaoService) {
		this.notificacaoService = notificacaoService;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int save() {
		// testar se notificacao eh valido e outras regras de negocio

		ModelMapper modelMapper = new ModelMapper();
		NotificacaoEventData notificacaoData = modelMapper.map(this, NotificacaoEventData.class);

		this.id = notificacaoService.persist(notificacaoData);

		return this.id;
	}
}
