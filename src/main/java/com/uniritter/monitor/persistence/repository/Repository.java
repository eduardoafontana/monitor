package com.uniritter.monitor.persistence.repository;

import java.util.List;

import com.uniritter.monitor.domain.repository.IRepository;
import com.uniritter.monitor.domain.repository.model.GenericEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

public class Repository<T> implements IRepository {

	protected GenericDao dao;
	private Class<T> classObj;

	public Repository(GenericDao dao, Class<T> classObj) {
		this.dao = dao;
		this.classObj = classObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList() {
		return this.dao.<T>getList(classObj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListFromRelation(int relatedId) {
		return this.dao.<T> getFromParent(classObj, relatedId);
	}

	@Override
	public int save(GenericEventData entidade) {

		if(this.dao.registerExist(entidade.getId()))
			return this.dao.update(entidade);
		else
			return this.dao.create(entidade);
	}

	@Override
	public int saveToRelation(GenericEventData entidade, int relatedId) {

		if(this.dao.registerExist(entidade.getId()))
			return this.dao.update(entidade, relatedId);
		else
			return this.dao.create(entidade, relatedId);
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T getById(int id) {

		return (T) this.dao.getById(classObj, id);
	}
	
	@Override
	public int deleteById(int id) {

		return this.dao.delete(id);
	}

	@Override
	public int deleteFromRelation(int relatedId) {
		return this.dao.deleteFromParent(relatedId);
	}
}
