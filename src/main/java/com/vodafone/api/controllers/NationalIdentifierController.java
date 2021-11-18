package com.vodafone.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.api.dtos.NationalIdentifierValidationDTO;
import com.vodafone.api.services.INationalIdentifierService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("nationalIdentifier")
public class NationalIdentifierController {
	
	private INationalIdentifierService nationalIdentifierService;

	@Autowired
	public NationalIdentifierController(INationalIdentifierService nationalIdentifierService) {
		this.nationalIdentifierService = nationalIdentifierService;
	}

	@ApiOperation(value = "checkNationalCode", response = NationalIdentifierValidationDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "NationalIdentifier verified") })
	@GetMapping("/check/{nationalIdentifier}")
	public NationalIdentifierValidationDTO getDiscount(@ApiParam(name = "nationalIdentifier", value = "National identifier")
	@PathVariable String nationalIdentifier) {
		return nationalIdentifierService.checkNationalIdentifier(nationalIdentifier);
	}

}
