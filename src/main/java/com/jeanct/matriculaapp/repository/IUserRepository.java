package com.jeanct.matriculaapp.repository;

import com.jeanct.matriculaapp.model.User;

public interface IUserRepository extends IGenericRepo<User, Integer>{

    User findOneByUsername(String username);
}
