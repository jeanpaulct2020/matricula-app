package com.jeanct.matriculaapp.controller;

import com.jeanct.matriculaapp.dto.EnrollmentDTO;
import com.jeanct.matriculaapp.model.Enrollment;
import com.jeanct.matriculaapp.service.IEnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    @Qualifier("enrollmentMapper")
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception {
        List<EnrollmentDTO> list = enrollmentService.readAll()
                .stream().map( enrollment -> mapper.map(enrollment, EnrollmentDTO.class) )
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("course-student")
    public ResponseEntity<Map<String,  List<String>>> readByCourseAndStudents() throws Exception {
        Map<String,  List<String>> mapCourseStudents = enrollmentService.getCoursesWithStudents();

        return new ResponseEntity<>(mapCourseStudents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id)throws Exception{
        Enrollment enrollment = enrollmentService.readById(id);
        /*if(enrollment == null){
            throw new ModelNotFoundException("enrollment with ID: " + id + " not found");
        }*/
        EnrollmentDTO enrollmentDTO = mapper.map(enrollment, EnrollmentDTO.class);
        return new ResponseEntity<>(enrollmentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> createEnrollment(@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws Exception {
        Enrollment enrollmentModel = mapper.map(enrollmentDTO, Enrollment.class);
        EnrollmentDTO enrollmentSaved = mapper.map(enrollmentService.save(enrollmentModel), EnrollmentDTO.class);

        return new ResponseEntity<>(enrollmentSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnrollmentDTO> updatEenrollment(@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws Exception {
        Enrollment enrollmentModel = mapper.map(enrollmentDTO, Enrollment.class);
        EnrollmentDTO enrollmentSaved = mapper.map(enrollmentService.update(enrollmentModel), EnrollmentDTO.class);

        return new ResponseEntity<>(enrollmentSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletEenrollment(@PathVariable("id") Integer id) throws Exception {
        Enrollment enrollment = enrollmentService.readById(id);
        if(enrollment != null){
            enrollmentService.delete(id);
        }
        return new ResponseEntity<>("enrollment deleted", HttpStatus.OK);
    }

}
