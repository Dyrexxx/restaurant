package ru.pizza.restaurant.exceptions;

import lombok.Getter;

@Getter
public class NoContentException extends RuntimeException {
    /**
     * {@link NoContentException#page}
     * Содержит html страницу
     */
    private final String page;
    /**
     * id если на странице есть ссылки требующие его
     */
    private Integer id;
    public NoContentException(String page) {
        super("Пусто");
        this.page = page;
    }
    public NoContentException(String page, Integer id) {
        super("Пусто");
        this.page = page;
        this.id = id;
    }
}
