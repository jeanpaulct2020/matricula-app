package com.jeanct.matriculaapp.service;

import java.util.List;

public interface ICRUDService<T, ID>{

    List<T> readAll() throws Exception;

    T readById(ID id);

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    void delete(ID id) throws Exception;
}
