package dev.patika.fourthhomework.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T>{

    List<T> findAll();

    T findByName(String name);

    T save(T object);

    void deleteByName(String name);

    T update(T object);


}
