package com.vodafone.api.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vodafone.api.dtos.CalculateNationalIdentifierInputDTO;
import com.vodafone.api.dtos.NationalIdentifierDataDTO;
import com.vodafone.api.dtos.NationalIdentifierValidationDTO;
import com.vodafone.api.entities.City;
import com.vodafone.api.mappers.CityMapper;
import com.vodafone.api.repositories.CityRepository;
import com.vodafone.api.utils.*;

@Service
public class NationalIdentifierService implements INationalIdentifierService {
	
	private CityRepository cityRepository;
	private CityMapper cityMapper;
	
	@Autowired
	public NationalIdentifierService(CityRepository cityRepository, CityMapper cityMapper) {
		this.cityRepository = cityRepository;
		this.cityMapper = cityMapper;
	}

	@Override
	public NationalIdentifierValidationDTO checkNationalIdentifier(String nationalIdentifier) {
		String digitOmocodiaLettersRegEx = "[0-9"
				+ Constants.valueOmocodiaLetterMap.values().stream().map(x -> String.valueOf(x)).collect(Collectors.joining())
				+ "]";

		if (nationalIdentifier == null || (nationalIdentifier.length() != 16 
				//&& nationalIdentifier.length() != 11
				)) {
			return NationalIdentifierValidationDTO.fail();
		}
		
		nationalIdentifier = nationalIdentifier.toUpperCase();
		
		if (!nationalIdentifier.substring(0, 3).matches("[A-Z]{3}")) {
			return NationalIdentifierValidationDTO.fail();
		}
		if (!nationalIdentifier.substring(3, 6).matches("[A-Z]{3}")) {
			return NationalIdentifierValidationDTO.fail();
		}
		if (!nationalIdentifier.substring(6, 8).matches(digitOmocodiaLettersRegEx + "{2}")) {
			return NationalIdentifierValidationDTO.fail();
		}
		if (!Constants.letterMonthMap.keySet().contains(nationalIdentifier.charAt(8))) {
			return NationalIdentifierValidationDTO.fail();
		}
		if (!nationalIdentifier.substring(9, 11).matches(digitOmocodiaLettersRegEx + "{2}")) {
			return NationalIdentifierValidationDTO.fail();
		}

		Character firstYearSymbol = getValueOfOmocodiaLetter(nationalIdentifier.charAt(6));
		Character secondYearSymbol = getValueOfOmocodiaLetter(nationalIdentifier.charAt(7));
		Integer year = Integer.parseInt(String.valueOf(firstYearSymbol) + secondYearSymbol);

		Integer month = Constants.letterMonthMap.get(nationalIdentifier.charAt(8));

		Character firstDaysymbol = getValueOfOmocodiaLetter(nationalIdentifier.charAt(9));
		Character secondDaysymbol = getValueOfOmocodiaLetter(nationalIdentifier.charAt(10));
		Integer day = Integer.parseInt(String.valueOf(firstDaysymbol) + secondDaysymbol);

		if (day == 0 || (day > 31 && day < 41) || day > 71) {
			return NationalIdentifierValidationDTO.fail();
		}

		boolean male = true;
		if (day >= 40) {
			male = false;
		}

		int localYear = LocalDate.now().getYear();

		LocalDate birthDate = LocalDate.parse(
				String.valueOf(localYear).substring(0, 2) + year + "-" + String.format("%02d", month) + "-" + day,
				DateTimeFormatter.ISO_DATE);

		if (birthDate.compareTo(LocalDate.now()) >= 0) {
			birthDate = birthDate.minus(100, ChronoUnit.YEARS);
		}

		if (!nationalIdentifier.substring(11, 15).matches("[A-Z]" + digitOmocodiaLettersRegEx + "{3}")) {
			return NationalIdentifierValidationDTO.fail();
		}
		
		List<City> birthCities = cityRepository.findByCadastralCode(nationalIdentifier.substring(11, 15));
		
		if (birthCities.isEmpty()) {
			return NationalIdentifierValidationDTO.fail();
		}
		

		Character checkDigit = nationalIdentifier.charAt(15);

		if (!Constants.valueCheckDigitMap.values().contains(checkDigit)) {
			return NationalIdentifierValidationDTO.fail();
		}

		int total = 0;

		boolean isDigitLetter = false;
		for (int i = 0; i < nationalIdentifier.length() - 1; i++) {
			Character symbol = nationalIdentifier.charAt(i);
			if (Constants.digitPositions.contains(i)) {
				if (!Character.isDigit(symbol)) {
					isDigitLetter = true;
				}
				if (isDigitLetter && Character.isDigit(symbol)) {
					return NationalIdentifierValidationDTO.fail();
				}
			}
			total += i % 2 == 1 ? Constants.evenValueMap.get(symbol) : Constants.oddValueMap.get(symbol);
		}

		if (!Constants.valueCheckDigitMap.get(total % 26).equals(checkDigit)) {
			return NationalIdentifierValidationDTO.fail();
		}
				
		NationalIdentifierDataDTO data = new NationalIdentifierDataDTO();
		data.setBirthDate(birthDate);
		data.setMale(male);
		data.setBirthCity(cityMapper.entityToDTO(birthCities.get(0)));

		return NationalIdentifierValidationDTO.success(data);
	}

	@Override
	public String calculateNationalIdentifier(CalculateNationalIdentifierInputDTO input) throws Exception {

		String nationalIdentifier = "";

		nationalIdentifier += formatLastName(input.getLastName().toUpperCase());
		nationalIdentifier += formatFirstName(input.getFirstName().toUpperCase());
		nationalIdentifier += String.valueOf(input.getBirthDate().getYear()).substring(2);
		nationalIdentifier += Constants.letterMonthMap.entrySet().stream().filter(x -> x.getValue() == input.getBirthDate().getMonthValue())
				.map(Entry::getKey).findFirst().get();
		nationalIdentifier += input.getBirthDate().getDayOfMonth() + (input.isMale() ? 0 : 30);
		
		List<City> cities = cityRepository.findByName(input.getBirthCity());
		
		if (!cities.isEmpty()) {
			nationalIdentifier += cities.get(0).getCadastralCode().toUpperCase();
		} else {
			throw new Exception("City not found");
		}
		
		int total = 0;
		for (int i = 0; i < nationalIdentifier.length(); i++) {
			Character symbol = nationalIdentifier.charAt(i);
			total += i % 2 == 1 ? Constants.evenValueMap.get(symbol) : Constants.oddValueMap.get(symbol);
		}

		nationalIdentifier += Constants.valueCheckDigitMap.get(total % 26);

		return nationalIdentifier;
	}

	private static String formatLastName(String lastName) {
		String formattedLastName = "";
		List<Character> stringVowels = new ArrayList<>();
		for (int i = 0; i < lastName.length(); i++) {
			Character c = lastName.charAt(i);
			if (c == ' ') {
				continue;
			}
			if (Constants.vowels.contains(c)) {
				stringVowels.add(c);
			} else {
				formattedLastName += c;
			}
			if (formattedLastName.length() == 3) {
				break;
			}
		}

		if (formattedLastName.length() != 3 && !stringVowels.isEmpty()) {
			formattedLastName += stringVowels.subList(0, Math.min(stringVowels.size(), 3 - formattedLastName.length()))
					.stream().map(String::valueOf).collect(Collectors.joining());
		}

		return String.format("%-3s", formattedLastName).replace(' ', 'X');
	}

	private static String formatFirstName(String firstName) {
		String formattedFirstName = "";
		Character secondConsonant = null;
		List<Character> stringVowels = new ArrayList<>();
		for (int i = 0; i < firstName.length(); i++) {
			Character c = firstName.charAt(i);
			if (c == ' ') {
				continue;
			}
			if (Constants.vowels.contains(c)) {
				stringVowels.add(c);
			} else {
				if (formattedFirstName.length() == 1 && secondConsonant == null) {
					secondConsonant = c;
					continue;
				}
				formattedFirstName += c;
			}
			if (formattedFirstName.length() == 3) {
				break;
			}
		}

		if (formattedFirstName.length() != 3 && secondConsonant != null) {
			formattedFirstName = formattedFirstName.substring(0, 1) + secondConsonant + formattedFirstName.substring(1);
		}

		if (formattedFirstName.length() != 3 && !stringVowels.isEmpty()) {
			formattedFirstName += stringVowels
					.subList(0, Math.min(stringVowels.size(), 3 - formattedFirstName.length())).stream()
					.map(String::valueOf).collect(Collectors.joining());
		}

		return String.format("%-3s", formattedFirstName).replace(' ', 'X');
	}

	private static Character getValueOfOmocodiaLetter(Character symbol) {
		if (Constants.valueOmocodiaLetterMap.values().contains(symbol)) {
			Character finalsymbol = symbol;
			symbol = Character.forDigit(Constants.valueOmocodiaLetterMap.entrySet().stream()
					.filter(x -> x.getValue() == finalsymbol).map(Map.Entry::getKey).findFirst().get(), 10);
		}
		return symbol;
	}


}
