package com.uniritter.monitor.domain.repository;

import java.util.List;

public interface IRepository {
	public List<? extends IEntity> getList();
	public int save(IEntity entidade);
}
