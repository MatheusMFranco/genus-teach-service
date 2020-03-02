package com.genus.teach.exception;

public class WrongLanguageException extends Exception {

	public WrongLanguageException(String language) {
		super(language + " not found on database.");
	}

}
