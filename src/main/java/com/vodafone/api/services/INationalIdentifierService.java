package com.vodafone.api.services;

import com.vodafone.api.dtos.NationalIdentifierValidationDTO;

public interface INationalIdentifierService {
	
	public NationalIdentifierValidationDTO checkNationalIdentifier(String nationalIdentifier);

}
