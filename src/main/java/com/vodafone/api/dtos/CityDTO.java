package com.vodafone.api.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "City")
public class CityDTO {
	@ApiModelProperty(value = "City name")
	private String name;
	@ApiModelProperty(value = "Province abbreviation")
	private String province;
	@ApiModelProperty(value = "City istat identifier")
	private String identifier;
}
