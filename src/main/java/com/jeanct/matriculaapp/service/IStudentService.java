package com.jeanct.matriculaapp.service;

import com.jeanct.matriculaapp.model.Student;

import java.util.List;

public interface IStudentService extends ICRUDService<Student, Integer>{

    List<Student> getAllStudentByAge();
}
