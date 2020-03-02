package com.genus.teach.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TEACHER")
public class Teacher {

    public Teacher(
            Long id,
            String name,
            String language,
            String photo,
            Boolean status,
            LocalDate date
    ) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.photo = photo;
        this.status = status;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private BigDecimal score;

    @NotNull
    private BigDecimal cash;

    @NotNull
    private Boolean status;

    @NotBlank
    private String name;

    private String photo;

    @NotNull
    private String language;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

}
