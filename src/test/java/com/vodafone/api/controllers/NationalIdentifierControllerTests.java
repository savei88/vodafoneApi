package com.vodafone.api.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vodafone.api.dtos.NationalIdentifierValidationDTO;
import com.vodafone.api.entities.City;
import com.vodafone.api.repositories.CityRepository;

@SpringBootTest
public class NationalIdentifierControllerTests {

	private static String rightNationalIdentifier = "PRVPRV80A01H501K";

	@Autowired
	private NationalIdentifierController nationalIdentifierController;

	@MockBean
	private CityRepository cityRepository;

	@Test
	void getNationalIdentifiers() {
		City city = new City();
		city.setCadastralCode("H501");
		city.setIdentifier("XXX");
		city.setName("ROMA");
		city.setProvince("RO");
		Mockito.when(cityRepository.findByCadastralCode("H501")).thenReturn(Arrays.asList(city));

		NationalIdentifierValidationDTO result = nationalIdentifierController
				.checkNationalIdentifier(rightNationalIdentifier);

		assertEquals(true, result.isValid());
	}

}
