package com.genus.teach.service;

import com.genus.teach.exception.WrongLanguageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenusService<T, D, F> {

    Long count(F filter);
    Page<T> find(F filter, Pageable pageable);
    T findById(Long id);
    Boolean exists(Long id);
    T save(D entity) throws WrongLanguageException;

}
