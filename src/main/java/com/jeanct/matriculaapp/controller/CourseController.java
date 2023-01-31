package com.jeanct.matriculaapp.controller;

import com.jeanct.matriculaapp.dto.CourseDTO;
import com.jeanct.matriculaapp.model.Course;
import com.jeanct.matriculaapp.service.ICourseService;
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
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    @Qualifier("courseMapper")
    private ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception {
        List<CourseDTO> list = courseService.readAll()
                .stream().map( course -> mapper.map(course, CourseDTO.class) )
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id)throws Exception{
        Course course = courseService.readById(id);
        /*if(Course == null){
            throw new ModelNotFoundException("Course with ID: " + id + " not found");
        }*/
        CourseDTO CourseDTO = mapper.map(course, CourseDTO.class);
        return new ResponseEntity<>(CourseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) throws Exception {
        Course courseModel = mapper.map(courseDTO, Course.class);
        CourseDTO courseSaved = mapper.map(courseService.save(courseModel), CourseDTO.class);

        return new ResponseEntity<>(courseSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> updateCourse(@Valid @RequestBody CourseDTO courseDTO) throws Exception {
        Course courseModel = mapper.map(courseDTO, Course.class);
        CourseDTO courseSaved = mapper.map(courseService.update(courseModel), CourseDTO.class);

        return new ResponseEntity<>(courseSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Integer id) throws Exception {
        Course course = courseService.readById(id);
        if(course != null){
            courseService.delete(id);
        }
        return new ResponseEntity<>("Course deleted", HttpStatus.OK);
    }

}
