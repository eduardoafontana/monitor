package com.uniritter.monitor.domain.repository;

import com.uniritter.monitor.domain.repository.model.NotificacaoEventData;

public interface INotificacaoRepository extends IRepository {
	public NotificacaoEventData getLast();
}
