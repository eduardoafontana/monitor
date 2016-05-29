package com.uniritter.monitor.domain.repository;

import java.util.List;

public interface IRepository {
	public List<? extends IEntity> getList();
	public IEntity getById(int id);
	//public int save(IEntity entidade) throws Exception;
	public int save(IEntity entidade);
	public int deleteById(int id);
}
