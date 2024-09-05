package ru.pizza.restaurant.services;


import java.util.List;

public interface BaseMethodsService<T, V> {
    /***
     *
     * @return List всех данных базы данных
     */
    List<T> index();
}
