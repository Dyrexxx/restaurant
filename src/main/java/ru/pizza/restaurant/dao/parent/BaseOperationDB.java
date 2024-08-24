package ru.pizza.restaurant.dao.parent;

import java.util.Collections;
import java.util.List;

public interface BaseOperationDB<T, V> {
    List<T> findAll();

    T findOneById(V id);


}
