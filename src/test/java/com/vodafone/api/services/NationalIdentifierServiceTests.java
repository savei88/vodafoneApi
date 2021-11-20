package com.vodafone.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.vodafone.api.dtos.CalculateNationalIdentifierInputDTO;
import com.vodafone.api.dtos.CityDTO;
import com.vodafone.api.dtos.NationalIdentifierValidationDTO;
import com.vodafone.api.entities.City;
import com.vodafone.api.exceptions.UnprocessableEntityException;
import com.vodafone.api.mappers.CityMapper;
import com.vodafone.api.repositories.CityRepository;

@SpringBootTest
public class NationalIdentifierServiceTests {

	private static String rightNationalIdentifier = "PRVPRV80A01H501K";

	@Mock
	private CityRepository cityRepository;

	@Mock
	private CityMapper cityMapper;

	@InjectMocks
	private NationalIdentifierService nationalIdentifierService;

	private static Stream<Arguments> firstNameDatas() {
		return Stream.of(Arguments.of("PROVA", "PRV"), Arguments.of("PROVALO", "PVL"), Arguments.of("PARA", "PRA"),
				Arguments.of("P", "PXX"), Arguments.of("AP", "PAX"));
	}

	private static Stream<Arguments> lastNameDatas() {
		return Stream.of(Arguments.of("PROVA", "PRV"), Arguments.of("PROVALO", "PRV"), Arguments.of("PARA", "PRA"),
				Arguments.of("P", "PXX"), Arguments.of("AP", "PAX"));
	}

	private static Stream<Arguments> nationalIdentifierDatas() {
		return Stream.of(Arguments.of(""), Arguments.of("PRPRPRP"), Arguments.of("PRPRPRP"),
				Arguments.of("1RVPRV80A01H501K"), 
				Arguments.of("PRV1RV80A01H501K"), Arguments.of("PRVPRVT0A01H501K"),
				Arguments.of("PRVPRV80101H501K"), Arguments.of("PRVPRV80AT1H501K"), 
				Arguments.of("PRVPRV80A01H5T1K"),
				Arguments.of("PRVPRV80A01H501R"),
				Arguments.of("PRVPRV80A32H501K")

		);
	}

	@ParameterizedTest(name = "{index} => firstName={0}, result={1}")
	@MethodSource("firstNameDatas")
	void formatFirstName(String firstName, String result) {
		assertEquals(result, NationalIdentifierService.formatFirstName(firstName));
	}

	@ParameterizedTest(name = "{index} => lastName={0}, result={1}")
	@MethodSource("lastNameDatas")
	void formatLastName(String lastName, String result) {
		assertEquals(result, NationalIdentifierService.formatLastName(lastName));
	}

	@ParameterizedTest(name = "{index} => nationalIdentifier={0}")
	@MethodSource("nationalIdentifierDatas")
	void checkNationalIdentifierWhenWrongReturnFalse(String nationalIdentifier) {
		assertEquals(false, nationalIdentifierService.checkNationalIdentifier(nationalIdentifier).isValid());
	}
	
	@Test
	void checkNationalIdentifierWhenCityNotFoundReturnFalse() {
		Mockito.when(cityRepository.findByCadastralCode("H501")).thenReturn(new ArrayList<>());
		assertEquals(false, nationalIdentifierService.checkNationalIdentifier(rightNationalIdentifier).isValid());
	}
	
	@Test
	void checkNationalIdentifierWhenRightReturnValidData() {
		City city = new City("XXX","ROMA", "RM", "H501");
		CityDTO dto = new CityDTO(city.getName(), city.getProvince(), city.getCadastralCode());
		Mockito.when(cityRepository.findByCadastralCode("H501")).thenReturn(Arrays.asList(city));
		Mockito.when(cityMapper.entityToDTO(city)).thenReturn(dto);
		NationalIdentifierValidationDTO result = nationalIdentifierService.checkNationalIdentifier(rightNationalIdentifier);
		assertEquals(true, result.isValid());
		assertEquals(dto, result.getData().getBirthCity());
		assertEquals(true, result.getData().isMale());
		assertEquals("1980-01-01", result.getData().getBirthDate().toString());
	}
	
	@Test
	void calculateNationalIdentifierWhenCityRightReturnValidData() {
		City city = new City("XXX","ROMA", "RM", "H501");
		CityDTO dto = new CityDTO(city.getName(), city.getProvince(), city.getCadastralCode());
		Mockito.when(cityRepository.findByNameAndProvince("Roma", "RM")).thenReturn(Arrays.asList(city));
		Mockito.when(cityMapper.entityToDTO(city)).thenReturn(dto);
		CalculateNationalIdentifierInputDTO input = new CalculateNationalIdentifierInputDTO(
				"PROVA", "PROVA", true, LocalDate.parse("1980-01-01"), "Roma", "RM");
		String result = nationalIdentifierService.calculateNationalIdentifier(input);
		assertEquals(rightNationalIdentifier, result);
	}
	
	@Test
	void calculateNationalIdentifierWhenCityWrongThrowsException() {
		
		Mockito.when(cityRepository.findByNameAndProvince("Roma", "RM")).thenReturn(Arrays.asList());
		CalculateNationalIdentifierInputDTO input = new CalculateNationalIdentifierInputDTO(
				"PROVA", "PROVA", true, LocalDate.parse("1980-01-01"), "Roma", "RM");
		UnprocessableEntityException ex = assertThrows(UnprocessableEntityException.class, 
				() -> nationalIdentifierService.calculateNationalIdentifier(input));
		assertEquals("City not found", ex.getMessage());
	}

}
