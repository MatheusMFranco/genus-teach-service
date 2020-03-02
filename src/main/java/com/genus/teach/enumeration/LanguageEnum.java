package com.genus.teach.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum LanguageEnum {

	PT_BR("pt-BR", "português", "Brazil"),
	EN_US("en-US", "english", "United States");

	private String code;
	private String name;
	private String country;

	public static Optional<LanguageEnum> find(String code) {
		return Arrays.stream(values())
				.filter(language -> language.code.equals(code))
				.findFirst();
	}

}
