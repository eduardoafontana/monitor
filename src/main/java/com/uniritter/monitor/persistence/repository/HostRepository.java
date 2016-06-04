package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Observable;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Host;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.repository.IHostRepository;
import com.uniritter.monitor.persistence.model.HostEntity;
import com.uniritter.monitor.persistence.service.HostDao;

@Component
public class HostRepository implements IHostRepository {
	
	private final HostDao hostDao;
	
	@Autowired
	public HostRepository(HostDao hostDao){
		this.hostDao = hostDao;
	}
	
	@Override
	public List<? extends IEntity> getList() {
		List<HostEntity> hostEntity = this.hostDao.getHosts();
		
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<Host>>() {}.getType();
		List<Host> hosts = modelMapper.map(hostEntity, listType);	
		
		return hosts;
	}
	
	@Override
	public List<? extends IEntity> getListFromParent(int parentId) {
		List<HostEntity> hostEntity = this.hostDao.getHostsFromParent(parentId);
		
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<Host>>() {}.getType();
		List<Host> hosts = modelMapper.map(hostEntity, listType);	
		
		return hosts;
	}
	
	@Override
	public int save(IEntity entidade) {
		//Host host = (Host)entidade;

		ModelMapper modelMapper = new ModelMapper();
		HostEntity hostEntity = modelMapper.map(entidade, HostEntity.class);
		
		return this.hostDao.createHost(hostEntity);
	}

	@Override
	public IEntity getById(int id) {

		HostEntity hostEntity = this.hostDao.getHost(id);
		
		ModelMapper modelMapper = new ModelMapper();
		IEntity entidade = modelMapper.map(hostEntity, IEntity.class);
		
		return entidade;
	}

	@Override
	public int deleteById(int id) {

		return this.hostDao.deleteHost(id);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
