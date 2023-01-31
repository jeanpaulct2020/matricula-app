package com.jeanct.matriculaapp.service.impl;

import com.jeanct.matriculaapp.model.Enrollment;
import com.jeanct.matriculaapp.model.EnrollmentDetail;
import com.jeanct.matriculaapp.model.Student;
import com.jeanct.matriculaapp.repository.IEnrollmentRepository;
import com.jeanct.matriculaapp.repository.IGenericRepo;
import com.jeanct.matriculaapp.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
public class EnrollmentServiceImpl extends CRUDServiceImpl<Enrollment, Integer> implements IEnrollmentService {

    @Autowired
    private IEnrollmentRepository enrollmentRepository;


    @Override
    protected IGenericRepo<Enrollment, Integer> getRepo() {
        return enrollmentRepository;
    }

    @Override
    public Map<String,  List<String>> getCoursesWithStudents() {

        Stream<List<EnrollmentDetail>> stream = enrollmentRepository.findAll().stream()
                .map(Enrollment::getDetails);

        Stream<EnrollmentDetail> streamDetail = stream.flatMap( Collection::stream);

        Map<String,List<String>> enrrollsByCourseAndStudents = streamDetail.
                collect(groupingBy(d -> d.getCourse().getName(), mapping(d -> d.getEnrollment().getStudent().getName(), toList())));
        return enrrollsByCourseAndStudents;
    }
}
