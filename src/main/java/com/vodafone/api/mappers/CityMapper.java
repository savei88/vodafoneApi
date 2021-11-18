package com.vodafone.api.mappers;

import org.mapstruct.Mapper;

import com.vodafone.api.dtos.CityDTO;
import com.vodafone.api.entities.City;

@Mapper(componentModel = "spring")
public interface CityMapper {

	public CityDTO entityToDTO(City entity);

}