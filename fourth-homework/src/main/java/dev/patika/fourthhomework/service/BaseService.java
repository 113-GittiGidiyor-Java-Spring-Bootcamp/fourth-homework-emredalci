package dev.patika.fourthhomework.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T>{

    List<T> findAll();

    T findByName(String name);

    //T save(T object);

    //T update(T object);

    void deleteById(long id);


}
