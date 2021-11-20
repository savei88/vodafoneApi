package com.vodafone.api.controllers;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vodafone.api.dtos.CalculateNationalIdentifierInputDTO;
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
	void checkNationalIdentifier() {
		City city = new City("XXX","ROMA", "RM", "H501");
		Mockito.when(cityRepository.findByCadastralCode("H501")).thenReturn(Arrays.asList(city));

		NationalIdentifierValidationDTO result = nationalIdentifierController
				.checkNationalIdentifier(rightNationalIdentifier);

		assertEquals(true, result.isValid());
	}

	@Test
	void calculateNationalIdentifier() {
		City city = new City("XXX","ROMA", "RM", "H501");
		Mockito.when(cityRepository.findByNameAndProvince("Roma", "RM")).thenReturn(Arrays.asList(city));

		String result = nationalIdentifierController
				.calculateNationalIdentifier(new CalculateNationalIdentifierInputDTO("PROVA", "PROVA", true,
						LocalDate.parse("1980-01-01"), "Roma", "RM"));

		assertEquals(rightNationalIdentifier, result);
	}

}
