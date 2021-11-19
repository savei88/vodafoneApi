package com.vodafone.api.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CalculateNationalIdentifierInputDTO {
	private String firstName;
	private String lastName;
	private boolean male;
	private LocalDate birthDate;
	private String birthCity;
}
