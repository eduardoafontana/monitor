package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.INotificacaoRepository;
import com.uniritter.monitor.domain.repository.model.NotificacaoEventData;

@Component
public class NotificacaoService {

	private final INotificacaoRepository notificacaoRepository;

	@Autowired
	public NotificacaoService(INotificacaoRepository notificacaoRepository) {
		this.notificacaoRepository = notificacaoRepository;
	}

	@SuppressWarnings("unchecked")
	public List<Notificacao> retrieveAll(int metricaId) {
		List<NotificacaoEventData> notificacaoEventData = (List<NotificacaoEventData>) notificacaoRepository.getListFromRelation(metricaId);

		List<Notificacao> notificacoes = new ArrayList<Notificacao>();

		for (NotificacaoEventData notificacaoData : notificacaoEventData) {

			Notificacao notificacao = new Notificacao(this);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(notificacaoData, notificacao);

			notificacoes.add(notificacao);
		}

		return notificacoes;
	}

	public int create(String mensagem) {

		Notificacao notificacao = new Notificacao(this);
		notificacao.setMensagem(mensagem);

		return notificacao.save();
	}

	public int persist(NotificacaoEventData notificacaoEventData) {
		return notificacaoRepository.save(notificacaoEventData);
	}
}
