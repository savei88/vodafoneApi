package com.vodafone.api.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel(description = "Data to calculate national identifier")
@AllArgsConstructor
public class CalculateNationalIdentifierInputDTO {
	@NotBlank(message = "Missing firstName")
	@ApiModelProperty(value = "FirstName", example = "PROVA")
	private String firstName;
	@NotBlank(message = "Missing lastName")
	@ApiModelProperty(value = "LastName", example = "PROVA")
	private String lastName;
	@NotNull(message = "Missing male")
	@ApiModelProperty(value = "Sex", example = "true")
	private boolean male;
	@NotNull(message = "Missing birthDate")
	@ApiModelProperty(value = "Birthdate in format YYYY-MM-DD", example = "1980-01-01")
	private LocalDate birthDate;
	@NotBlank(message = "Missing bithCity")
	@ApiModelProperty(value = "Birth city name", example = "ROMA")
	private String birthCity;
	@NotBlank(message = "Missing province")
	@ApiModelProperty(value = "Province abbreviation of birth city", example = "RM")
	private String province;
}
