package com.jeanct.matriculaapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Integer idStudent;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50)
    private String nameStudent;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50)
    private String lastnameStudent;

    @NotEmpty
    @NotNull
    @Size(min = 10, max = 50)
    private String dniStudent;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateStudent;
}
