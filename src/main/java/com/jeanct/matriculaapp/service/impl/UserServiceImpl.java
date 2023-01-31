package com.jeanct.matriculaapp.service.impl;

import com.jeanct.matriculaapp.model.Student;
import com.jeanct.matriculaapp.model.User;
import com.jeanct.matriculaapp.repository.IGenericRepo;
import com.jeanct.matriculaapp.repository.IStudentRepository;
import com.jeanct.matriculaapp.repository.IUserRepository;
import com.jeanct.matriculaapp.service.IStudentService;
import com.jeanct.matriculaapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDServiceImpl<User, Integer> implements IUserService {

    @Autowired
    private IUserRepository userRepo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return userRepo;
    }
}
