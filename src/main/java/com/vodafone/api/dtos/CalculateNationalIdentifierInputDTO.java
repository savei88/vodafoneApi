package com.vodafone.api.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CalculateNationalIdentifierInputDTO {
	@NotBlank(message="Missing firstName")
	private String firstName;
	@NotBlank(message="Missing lastName")
	private String lastName;
	@NotNull(message="Missing male")
	private boolean male;
	@NotNull(message="Missing birthDate")
	private LocalDate birthDate;
	@NotBlank(message="Missing bithCity")
	private String birthCity;
}
