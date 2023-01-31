package com.jeanct.matriculaapp.service.impl;

import com.jeanct.matriculaapp.exception.ModelNotFoundException;
import com.jeanct.matriculaapp.repository.IGenericRepo;
import com.jeanct.matriculaapp.service.ICRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

public abstract class CRUDServiceImpl<T, ID> implements ICRUDService<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) {
        return getRepo().findById(id).orElseThrow( () -> new ModelNotFoundException( id + " NOT FOUND"));
    }

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
