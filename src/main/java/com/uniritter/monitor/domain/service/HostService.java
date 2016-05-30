package com.uniritter.monitor.domain.service;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;

@Component
public class HostService {

	public String[] getGrupos() {
		return Arrays.toString(HostGrupo.values()).split(", ");
	}
}
