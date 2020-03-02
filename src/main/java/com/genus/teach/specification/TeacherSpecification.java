package com.genus.teach.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.genus.teach.model.Teacher;

public class TeacherSpecification {

	public static Optional<Specification<Teacher>> like(String key, Optional<String> value) {
		if (isBlank(value)) {
			return Optional.empty();
		}
		final String like = "%";
		StringBuilder builder = new StringBuilder(like)
				.append(value.get())
				.append(like);
		Specification<Teacher> specification = (root, criteriaQuery, criteriaBuilder) ->
				criteriaBuilder.like(root.get(key), builder.toString());
		return Optional.of(specification);
	}

	public static Optional<Specification<Teacher>> equal(String key, Optional<String> value) {
		if (isBlank(value)) {
			return Optional.empty();
		}
		Specification<Teacher> specification = (root, criteriaQuery, criteriaBuilder) ->
				criteriaBuilder.equal(root.get(key), value.get());
		return Optional.of(specification);
	}

	public static Optional<Specification<Teacher>> between(String key, Optional<String> start, Optional<String> end) {
		if (!start.isPresent() && !end.isPresent()) {
			return Optional.empty();
		}
		Specification<Teacher> less = (root, criteriaQuery, criteriaBuilder) ->
				criteriaBuilder.lessThanOrEqualTo(root.get(key), end.get().toString());
		Specification<Teacher> greater = (root, criteriaQuery, criteriaBuilder) ->
				criteriaBuilder.greaterThanOrEqualTo(root.get(key), start.get().toString());
		return Optional.of(less.and(greater));
	}

	private static Boolean isBlank(Optional<String> value){
		return !value.isPresent() || !StringUtils.hasText(value.get());
	}

}
