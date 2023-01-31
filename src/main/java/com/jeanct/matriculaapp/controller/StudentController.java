package com.jeanct.matriculaapp.controller;

import com.jeanct.matriculaapp.dto.StudentDTO;
import com.jeanct.matriculaapp.exception.ModelNotFoundException;
import com.jeanct.matriculaapp.model.Student;
import com.jeanct.matriculaapp.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    @Qualifier("studentMapper")
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception {
        List<StudentDTO> list = studentService.readAll()
                .stream().map( student -> mapper.map(student, StudentDTO.class) )
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<List<StudentDTO>> readAllByAge() throws Exception {
        List<StudentDTO> list = studentService.getAllStudentByAge()
                .stream().map( student -> mapper.map(student, StudentDTO.class) )
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id)throws Exception{
        Student student = studentService.readById(id);
        /*if(student == null){
            throw new ModelNotFoundException("Student with ID: " + id + " not found");
        }*/
        StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) throws Exception {
        Student studentModel = mapper.map(studentDTO, Student.class);
        StudentDTO studentSaved = mapper.map(studentService.save(studentModel), StudentDTO.class);

        return new ResponseEntity<>(studentSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDTO) throws Exception {
        Student studentModel = mapper.map(studentDTO, Student.class);
        StudentDTO studentSaved = mapper.map(studentService.update(studentModel), StudentDTO.class);

        return new ResponseEntity<>(studentSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) throws Exception {
        Student student = studentService.readById(id);
        if(student != null){
            studentService.delete(id);
        }
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

}
