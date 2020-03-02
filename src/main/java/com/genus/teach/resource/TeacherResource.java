package com.genus.teach.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.log4j.Log4j2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.genus.teach.model.Teacher;
import com.genus.teach.model.Teacher_;
import com.genus.teach.dto.TeacherDto;
import com.genus.teach.service.TeacherService;
import com.genus.teach.constant.TeacherConstant;
import com.genus.teach.enumeration.RequestLogEnum;
import com.genus.teach.util.FormatUtil;
import com.genus.teach.exception.WrongLanguageException;
import com.genus.teach.filter.TeacherFilter;

@Log4j2
@RestController
@RequestMapping("/teacher")
@Api("API REST Genus Teacher")
@CrossOrigin(origins = "*")
public class TeacherResource implements GenusResource<Teacher, TeacherDto, TeacherFilter> {

    @Autowired
    TeacherService service;

    @Override
    @GetMapping("/count")
    @ApiOperation("Return the amount of all teachers records")
    public ResponseEntity<Long> count(TeacherFilter filter) {
        log.info(FormatUtil.formatLogBuilder(
                RequestLogEnum.COUNT,
                TeacherConstant.ENTITY_ALL
        ));
        return new ResponseEntity<Long>(service.count(filter), HttpStatus.OK);
    }

    @Override
    @GetMapping
    @ApiOperation("Return the paginated list of all teachers")
    public ResponseEntity<Page<Teacher>> find(TeacherFilter filter, Pageable pageable) {
        log.info(FormatUtil.formatLogBuilder(
                RequestLogEnum.FIND,
                TeacherConstant.ENTITY_ALL, pageable
        ));
        return new ResponseEntity<Page<Teacher>>(service.find(filter, pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation("Return just one Teacher by id")
    public ResponseEntity<Teacher> findById(@PathVariable(Teacher_.ID) Long id) {
        log.info(FormatUtil.formatLogBy(
                RequestLogEnum.FIND,
                TeacherConstant.ENTITY_ONE,
                Teacher_.ID,
                id.toString()
        ));
        return new ResponseEntity<Teacher>(service.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("exists")
    @ApiOperation("Return true if teacher id exists")
    public ResponseEntity<Boolean> exists(@PathVariable(Teacher_.ID) Long id) {
        log.info(FormatUtil.formatLogBy(
                RequestLogEnum.EXISTS,
                TeacherConstant.ENTITY_ONE,
                Teacher_.ID,
                id.toString()
        ));
        return new ResponseEntity<Boolean>(service.exists(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    @ApiOperation("Save a Teacher on database")
    public ResponseEntity<Teacher> save(@RequestBody TeacherDto teacher) throws WrongLanguageException {
        log.info(FormatUtil.formatLogBuilder(
                RequestLogEnum.SAVE,
                TeacherConstant.ENTITY_ONE
        ));
        return new ResponseEntity<Teacher>(service.save(teacher), HttpStatus.CREATED);
    }

}
