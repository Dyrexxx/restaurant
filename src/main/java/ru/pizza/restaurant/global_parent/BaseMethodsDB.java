package ru.pizza.restaurant.global_parent;

import jakarta.transaction.Transactional;

import java.util.List;

public interface BaseMethodsDB<T, V> {
    List<T> findAll();
    T findById(V id);
    void save(T t);
    void deleteById(V id);

}