package com.vodafone.api.dtos;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Data calculated from nationalIdentifier")
public class NationalIdentifierDataDTO {
	@ApiModelProperty(value = "Birthdate in format YYYY-MM-DD", example = "1980-01-01")
	private LocalDate birthDate;
	@ApiModelProperty(value = "Sex", example = "true")
	private boolean male;
	private CityDTO birthCity;
}
