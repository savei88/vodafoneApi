package com.vodafone.api.dtos;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NationalIdentifierDataDTO {

	private LocalDate birthDate;
	private boolean male;
	private CityDTO birthCity;
}
