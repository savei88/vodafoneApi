package com.vodafone.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityDTO {

	private String name;
	private String province;
	private String postalCode;
}
