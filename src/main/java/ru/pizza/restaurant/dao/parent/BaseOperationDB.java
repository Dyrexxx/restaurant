package ru.pizza.restaurant.dao.parent;

public interface BaseOperationDB<T, V> {
    T findAll();

    T findOneById(V id);


}
