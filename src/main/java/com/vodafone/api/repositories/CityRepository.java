package com.vodafone.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vodafone.api.entities.City;


@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface CityRepository extends CrudRepository<City, String> {
	
	public List<City> findByCadastralCode(String cadastralCode);
}