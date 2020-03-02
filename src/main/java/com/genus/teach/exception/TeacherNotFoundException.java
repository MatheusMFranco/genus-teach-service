package com.genus.teach.exception;

public class TeacherNotFoundException extends Exception {

	public TeacherNotFoundException(Long id) {
		super(id + " Teacher ID not found on database.");
	}

}
