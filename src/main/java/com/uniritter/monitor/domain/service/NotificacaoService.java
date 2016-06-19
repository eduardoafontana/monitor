package com.uniritter.monitor.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.INotificacaoRepository;
import com.uniritter.monitor.domain.repository.model.NotificacaoEventData;

@Component
public class NotificacaoService {

	private final INotificacaoRepository notificacaoRepository;

	@Autowired
	public NotificacaoService(INotificacaoRepository notificacaoRepository) {
		this.notificacaoRepository = notificacaoRepository;
	}

	public String getUltimaMensagem() {
		NotificacaoEventData notificacaoEventData = notificacaoRepository.getLast();

		if (notificacaoEventData == null)
			return null;

		return notificacaoEventData.getMensagem();
	}

	public int create(String mensagem) {

		NotificacaoEventData notificacao = new NotificacaoEventData();
		notificacao.setMensagem(mensagem);

		return this.persist(notificacao);
	}

	public int persist(NotificacaoEventData notificacaoEventData) {
		return notificacaoRepository.save(notificacaoEventData);
	}
}
