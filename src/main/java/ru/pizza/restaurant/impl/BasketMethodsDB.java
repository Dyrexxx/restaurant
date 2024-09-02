package ru.pizza.restaurant.impl;

import java.util.List;

public interface BasketMethodsDB<T, V, K> extends BaseMethodsDB<T, V> {
    List<T> findAll(K id);
    void update(int buildingId, V id);
}
