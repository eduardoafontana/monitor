package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Medicao;
import com.uniritter.monitor.domain.repository.MedicaoEventData;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.repository.IMedicaoRepository;
import com.uniritter.monitor.persistence.model.MedicaoEntity;
import com.uniritter.monitor.persistence.service.MedicaoDao;

@Component
public class MedicaoRepository implements IMedicaoRepository {

	private final MedicaoDao medicaoDao;

	@Autowired
	public MedicaoRepository(MedicaoDao medicaoDao) {
		this.medicaoDao = medicaoDao;
	}

	@Override
	public List<? extends IEntity> getList() {
		List<MedicaoEntity> medicaoEntity = this.medicaoDao.getMedicaos();

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<Medicao>>() {
		}.getType();
		List<MedicaoEventData> medicaos = modelMapper.map(medicaoEntity, listType);

		return medicaos;
	}

	@Override
	public List<? extends IEntity> getListFromRelation(int relatedId) {
		List<MedicaoEntity> medicaoEntity = this.medicaoDao.getMedicaosFromParent(relatedId);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<MedicaoEventData>>() {
		}.getType();
		List<MedicaoEventData> medicaos = modelMapper.map(medicaoEntity, listType);

		return medicaos;
	}

	@Override
	public int save(IEntity entidade) {

		ModelMapper modelMapper = new ModelMapper();
		MedicaoEntity medicaoEntity = modelMapper.map(entidade, MedicaoEntity.class);

		return this.medicaoDao.createMedicao(medicaoEntity);
	}

	@Override
	public int saveToRelation(IEntity entidade, int relatedId) {

		ModelMapper modelMapper = new ModelMapper();
		MedicaoEntity medicaoEntity = modelMapper.map(entidade, MedicaoEntity.class);

		medicaoEntity.metricaId = relatedId;

		return this.medicaoDao.createMedicao(medicaoEntity);
	}

	@Override
	public IEntity getById(int id) {

		MedicaoEntity medicaoEntity = this.medicaoDao.getMedicao(id);

		ModelMapper modelMapper = new ModelMapper();
		IEntity entidade = modelMapper.map(medicaoEntity, IEntity.class);

		return entidade;
	}

	@Override
	public int deleteById(int id) {

		return this.medicaoDao.deleteMedicao(id);
	}

	@Override
	public int deleteFromRelation(int relatedId) {
		return this.medicaoDao.deleteMedicaosFromParent(relatedId);
	}
}
