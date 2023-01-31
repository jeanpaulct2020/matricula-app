package com.jeanct.matriculaapp.service;

import com.jeanct.matriculaapp.model.Enrollment;
import com.jeanct.matriculaapp.model.Student;

import java.util.List;
import java.util.Map;

public interface IEnrollmentService extends ICRUDService<Enrollment, Integer> {

    Map<String, List<String>> getCoursesWithStudents();

}
