package com.vodafone.api.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Data to calculate national identifier")
public class CalculateNationalIdentifierInputDTO {
	@NotBlank(message = "Missing firstName")
	@ApiModelProperty(value = "FirstName")
	private String firstName;
	@NotBlank(message = "Missing lastName")
	@ApiModelProperty(value = "LastName")
	private String lastName;
	@NotNull(message = "Missing male")
	@ApiModelProperty(value = "Sex")
	private boolean male;
	@NotNull(message = "Missing birthDate")
	@ApiModelProperty(value = "Birthdate in format YYYY-MM-DD")
	private LocalDate birthDate;
	@NotBlank(message = "Missing bithCity")
	@ApiModelProperty(value = "Birth city name")
	private String birthCity;
}
