package com.genus.teach.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestLogEnum {

	SAVE("save"),
	FIND("find"),
	COUNT("count"),
	EXISTS("verify");

	private String name;

}
