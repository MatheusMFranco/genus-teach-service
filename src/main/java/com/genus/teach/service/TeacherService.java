package com.genus.teach.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import com.genus.teach.model.Teacher;
import com.genus.teach.repository.TeacherRepository;
import com.genus.teach.dto.TeacherDto;
import com.genus.teach.enumeration.LanguageEnum;
import com.genus.teach.exception.WrongLanguageException;
import com.genus.teach.filter.TeacherFilter;
import static com.genus.teach.model.Teacher_.*;
import static com.genus.teach.specification.TeacherSpecification.*;

@Log4j2
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TeacherService implements GenusService<Teacher, TeacherDto, TeacherFilter> {

    @Autowired
    TeacherRepository repository;

    @Override
    public Long count(TeacherFilter filter) {
        return repository.count(filterFactory(filter));
    }

    @Override
    public Page<Teacher> find(TeacherFilter filter, Pageable pageable) {
        return repository.findAll(Specification.where(filterFactory(filter)), pageable);
    }

    @Override
    public Teacher findById(Long id) {
        Optional<Teacher> teacher = repository.findById(id);
        return teacher.isPresent() ? teacher.get() : null;
    }

    @Override
    public Boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Teacher save(TeacherDto dto) throws WrongLanguageException {
        LanguageEnum.find(dto.getLanguage())
                .orElseThrow(() -> new WrongLanguageException(dto.getLanguage()));
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
        return repository.save(teacher);
    }

    private Specification<Teacher> filterFactory(TeacherFilter filter) {
        return like(NAME, filter.getName()).get()
            .and(
                equal(LANGUAGE, filter.getLanguage()).get()
            ).and(
                equal(STATUS, filter.getStatus()).get()
            ).and(
                between(SCORE, filter.getStartScore(), filter.getEndScore()).get()
            ).and(
                between(CASH, filter.getStartCash(), filter.getEndCash()).get()
            ).and(
                between(DATE, filter.getStartDate(), filter.getEndDate()).get()
            );
    }

}
