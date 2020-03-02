package com.genus.teach.resource;

import com.genus.teach.exception.WrongLanguageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GenusResource<T, D, F> {

    ResponseEntity<Long> count(F filter);
    ResponseEntity<Page<T>> find(F filter, Pageable pageable);
    ResponseEntity<T> findById(Long id);
    ResponseEntity<T> save(D entity) throws WrongLanguageException;
    ResponseEntity<Boolean> exists(Long id);

}
