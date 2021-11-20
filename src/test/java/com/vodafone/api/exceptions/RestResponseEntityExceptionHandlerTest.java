package com.vodafone.api.exceptions;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vodafone.api.controllers.NationalIdentifierController;
import com.vodafone.api.services.INationalIdentifierService;

@SpringBootTest
public class RestResponseEntityExceptionHandlerTest {

	private MockMvc mvc;

	@Mock
	private INationalIdentifierService nationalIdentifierService;

	@InjectMocks
	private NationalIdentifierController nationalIdentifierController;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(nationalIdentifierController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void whenUnprocessableEntityThenUnprocessableEntity() throws Exception {
		Mockito.when(nationalIdentifierService.checkNationalIdentifier("MPL"))
				.thenThrow(new UnprocessableEntityException("Unprocessable"));

		MockHttpServletResponse response = mvc
				.perform(get("/nationalIdentifier/check/MPL"))
				.andReturn().getResponse();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatus());
	}

}
