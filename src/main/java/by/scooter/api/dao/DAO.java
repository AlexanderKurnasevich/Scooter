package by.scooter.api.dao;

import by.scooter.entity.AbstractEntity;

import java.util.List;

public interface DAO<T extends AbstractEntity> {

    T save(T entity);

    void delete(Long id);

    void update(T entity);

    List<T> getAll();

    List<T> getAll(Integer page, Integer size);

    Long getAllCount();

    T getById(Long id);

    void saveAll(List<T> list);

}