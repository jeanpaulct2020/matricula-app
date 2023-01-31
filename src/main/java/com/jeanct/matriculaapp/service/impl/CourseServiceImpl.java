package com.jeanct.matriculaapp.service.impl;

import com.jeanct.matriculaapp.model.Course;
import com.jeanct.matriculaapp.repository.ICourseRepository;
import com.jeanct.matriculaapp.repository.IGenericRepo;
import com.jeanct.matriculaapp.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends CRUDServiceImpl<Course, Integer> implements ICourseService {
    @Autowired
    private ICourseRepository courseRepository;


    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return courseRepository;
    }
}
