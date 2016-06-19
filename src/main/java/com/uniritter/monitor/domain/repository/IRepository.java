package com.uniritter.monitor.domain.repository;

import java.util.List;

import com.uniritter.monitor.domain.repository.model.GenericEventData;

public interface IRepository {

	public <T> List<T> getList();

	public <T> List<T> getListFromRelation(int relatedId);

	public <T> T getById(int id);

	public int save(GenericEventData entidade);

	public int saveToRelation(GenericEventData entidade, int parentId);

	public int deleteById(int id);

	public int deleteFromRelation(int relatedId);
}
