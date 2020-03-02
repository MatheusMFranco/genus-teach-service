package com.genus.teach.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.genus.teach.enumeration.LanguageEnum;
import com.genus.teach.model.Teacher;
import com.genus.teach.model.Teacher_;
import com.genus.teach.specification.TeacherSpecification;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherRepositoryTest {

	@Autowired
	private TeacherRepository repository;

	@Test
	public void shouldFindById() throws Exception {
		Teacher expected = factory();
		Optional<Teacher> optional = repository.findById(expected.getId());
		assertThat(optional.isPresent()).isTrue();
		Teacher teacher = optional.get();
		assertThat(teacher.getCash().longValue()).isEqualTo(expected.getCash().longValue());
		assertThat(teacher.getScore().longValue()).isEqualTo(expected.getScore().longValue());
		assertThat(teacher.getId()).isEqualTo(expected.getId());
		assertThat(teacher.getName()).isEqualTo(expected.getName());
		assertThat(teacher.getLanguage()).isEqualTo(expected.getLanguage());
		assertThat(teacher.getPhoto()).isEqualTo(expected.getPhoto());
		assertThat(teacher.getStatus()).isEqualTo(expected.getStatus());
	}

	@Test
	public void shouldNotFindById() throws Exception {
		Optional<Teacher> optional = repository.findById(4L);
		assertThat(optional.isPresent()).isFalse();
	}

	@Test
	public void shouldCountNameContainingIAndBrazilian() throws Exception {
		Long count = repository.count(Specification.where(
				TeacherSpecification.like(Teacher_.NAME, Optional.of("i")).get()
		).and(TeacherSpecification.equal(Teacher_.LANGUAGE, Optional.of(LanguageEnum.PT_BR.getCode())).get()));
		assertThat(count).isEqualTo(1L);
	}

	@Test
	public void shouldFilterNameContainingIAndBrazilian() throws Exception {
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")));
		Page list = repository.findAll(Specification.where(
						TeacherSpecification.like(Teacher_.NAME, Optional.of("i")).get()
		).and(TeacherSpecification.equal(Teacher_.LANGUAGE, Optional.of(LanguageEnum.PT_BR.getCode())).get()), pageable);
		assertThat(list.getTotalElements()).isEqualTo(1);
	}

	private Teacher factory() {
		return new Teacher(
				2L,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				Boolean.TRUE,
				"Joe Titor",
				"/img/titor.png",
				LanguageEnum.EN_US.getCode(),
				LocalDate.now()
		);
	}

}
