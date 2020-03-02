package com.genus.teach.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherDto {

    private Long id;
    private String name;
    private String photo;
    private String language;

}
