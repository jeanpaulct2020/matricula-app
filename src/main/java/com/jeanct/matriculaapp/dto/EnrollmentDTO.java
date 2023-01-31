package com.jeanct.matriculaapp.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDTO {


    private Integer idEnrollment;

    @NotNull
    @JsonIncludeProperties(value = {"idStudent"})
    private StudentDTO student;

    @NotNull
    @JsonIncludeProperties(value = {"idUser", "username"})
    private UserDTO user;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateEnrollmentDTO;

    @NotNull
    private boolean enabled;

    @NotNull
    @JsonManagedReference
    private List<EnrollmentDetailDTO> details;

}
