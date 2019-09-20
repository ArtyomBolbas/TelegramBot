package com.bolbas.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bolbas.app.entity.ReferenceInformation;

public interface RefInfoRepo extends CrudRepository<ReferenceInformation, Long> {

	ReferenceInformation findByCity(String city);

	@Transactional
	Long deleteByCity(String city);

}
