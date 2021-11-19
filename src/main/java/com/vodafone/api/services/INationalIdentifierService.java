package com.vodafone.api.services;

import com.vodafone.api.dtos.CalculateNationalIdentifierInputDTO;
import com.vodafone.api.dtos.NationalIdentifierValidationDTO;

public interface INationalIdentifierService {
	
	public NationalIdentifierValidationDTO checkNationalIdentifier(String nationalIdentifier);
	public String calculateNationalIdentifier(CalculateNationalIdentifierInputDTO input) throws Exception;
}
