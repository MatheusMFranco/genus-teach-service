package com.genus.teach.filter;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherFilter {

	private Optional<String> startScore;
	private Optional<String> endScore;
	private Optional<String> startCash;
	private Optional<String> endCash;
	private Optional<String> status;
	private Optional<String> name;
	private Optional<String> language;
	private Optional<String> startDate;
	private Optional<String> endDate;

}
