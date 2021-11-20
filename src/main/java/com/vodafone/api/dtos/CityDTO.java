package com.vodafone.api.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "City")
public class CityDTO {
	@ApiModelProperty(value = "City name", example = "ROMA")
	private String name;
	@ApiModelProperty(value = "Province abbreviation", example = "RM")
	private String province;
	@ApiModelProperty(value = "City istat identifier", example = "01001")
	private String identifier;
}
