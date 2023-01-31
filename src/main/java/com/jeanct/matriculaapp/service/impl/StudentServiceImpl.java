package com.jeanct.matriculaapp.service.impl;

import com.jeanct.matriculaapp.model.Student;
import com.jeanct.matriculaapp.repository.IGenericRepo;
import com.jeanct.matriculaapp.repository.IStudentRepository;
import com.jeanct.matriculaapp.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends CRUDServiceImpl<Student, Integer> implements IStudentService {

    @Autowired
    private IStudentRepository studentRepo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return studentRepo;
    }

    @Override
    public List<Student> getAllStudentByAge() {
        List<Student> studentList = studentRepo.findAll()
                .stream().sorted(Comparator.comparing(student -> student.getDate()))
                .collect(Collectors.toList());

        return studentList;
    }
}
