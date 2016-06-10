package com.uniritter.monitor.domain.repository;

import java.util.List;

import com.uniritter.monitor.domain.repository.model.GenericEventData;

public interface IRepository {
	public List<? extends GenericEventData> getList();

	public GenericEventData getById(int id);

	// public int save(IEntity entidade) throws Exception;
	public int save(GenericEventData entidade);

	public int saveToRelation(GenericEventData entidade, int parentId);

	public int deleteById(int id);

	public List<? extends GenericEventData> getListFromRelation(int relatedId);

	public int deleteFromRelation(int relatedId);
}
