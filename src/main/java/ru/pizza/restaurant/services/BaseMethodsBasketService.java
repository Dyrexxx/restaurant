package ru.pizza.restaurant.services;

import java.util.List;

public interface BaseMethodsBasketService<T,V> {
    /***
     * Используется когда нужно достать из базы данных определенные корзины
     *
     * @param id
     * @return List корзин отсортированных по id
     */
    List<T> index(V id);
}
