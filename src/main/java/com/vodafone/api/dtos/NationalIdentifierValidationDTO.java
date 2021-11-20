package com.vodafone.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(description = "Response of nationalIdentifier validation")
public class NationalIdentifierValidationDTO {
	@ApiModelProperty(value = "Indicate if nationalIdentifier is valid", example = "true")
	private boolean valid;
	private NationalIdentifierDataDTO data;
	
	public static NationalIdentifierValidationDTO fail() {
		return new NationalIdentifierValidationDTO(false, null);
	}
	
	public static NationalIdentifierValidationDTO success(NationalIdentifierDataDTO data) {
		return new NationalIdentifierValidationDTO(true, data);
	}
}
