package com.vodafone.api.services;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class NationalIdentifierServiceTests {

	private static Stream<Arguments> data() {
		return Stream.of(Arguments.of("PROVA", "PRV"), Arguments.of("PROVALO", "PVL"), Arguments.of("PARA", "PRA"),
				Arguments.of("P", "PXX"), Arguments.of("AP", "PAX"));
	}

	@ParameterizedTest(name = "{index} => firstName={0}, result={1}")
	@MethodSource("data")
	void formatFirstName(String firstName, String result) {
		assertEquals(result, NationalIdentifierService.formatFirstName(firstName));
	}

}
