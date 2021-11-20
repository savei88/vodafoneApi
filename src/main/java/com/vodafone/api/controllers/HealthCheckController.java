package com.vodafone.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("health")
public class HealthCheckController {

	@ApiOperation(value = "healtCheck")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HealthCheck OK") })
	@GetMapping("/check")
	public void check() {
	}

}
