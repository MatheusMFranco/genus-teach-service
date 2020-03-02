package com.genus.teach.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.genus.teach.dto.TeacherDto;
import com.genus.teach.enumeration.LanguageEnum;
import com.genus.teach.exception.WrongLanguageException;
import com.genus.teach.model.Teacher;
import com.genus.teach.repository.TeacherRepository;

@RunWith(SpringRunner.class)
public class TeacherServiceTest {

	@MockBean
	private TeacherRepository repository;
	private TeacherService service;
	private TeacherDto dto;

	@Before
	public void setUp() throws Exception {
		service = new TeacherService(repository);
		dto = factory();
		when(service.save(dto)).thenReturn(transform());
	}

	@Test
	public void shouldSave() throws Exception {
		service.save(dto);
		verify(repository).save(transform());
	}

	@Test(expected = WrongLanguageException.class)
	public void shouldNotSaveWrongLanguage() throws Exception {
		final TeacherDto teacher = dto;
		teacher.setLanguage("tlh");
		when(service.save(teacher)).thenReturn(null);
	}

	private TeacherDto factory() {
		return new TeacherDto(null, "Joe Due", "/img/due.png", LanguageEnum.PT_BR.getCode());
	}

	private Teacher transform() {
		Teacher teacher = new Teacher(
				dto.getId(),
				dto.getName(),
				dto.getLanguage(),
				dto.getPhoto(),
				Boolean.TRUE,
				LocalDate.now()
		);
		if (dto.getId() == null) {
			teacher.setScore(BigDecimal.ZERO);
			teacher.setCash(BigDecimal.ZERO);
		}
		return teacher;
	}

}
