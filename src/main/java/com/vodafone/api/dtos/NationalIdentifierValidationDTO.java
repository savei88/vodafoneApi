package com.vodafone.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NationalIdentifierValidationDTO {
	
	private boolean valid;
	private NationalIdentifierDataDTO data;
	
	public static NationalIdentifierValidationDTO fail() {
		return new NationalIdentifierValidationDTO(false, null);
	}
	
	public static NationalIdentifierValidationDTO success(NationalIdentifierDataDTO data) {
		return new NationalIdentifierValidationDTO(true, data);
	}
}
