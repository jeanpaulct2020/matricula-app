package com.jeanct.matriculaapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//si hay respuesta http cuyo valor sea nullo, no lo considera
public class CourseDTO {

    private Integer idCourse;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nameCourse;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 10)
    private String initialsCourse;

    @NotNull
    private boolean enabled;
}
