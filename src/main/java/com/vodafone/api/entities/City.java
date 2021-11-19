package com.vodafone.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_city")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class City {
	
	@Id
	@Column(name = "identifier", nullable = false)
	private String identifier;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "province", nullable = false)
	private String province;

	@Column(name = "cadastral_code", nullable = false)
	private String cadastralCode;
}
