package ru.pizza.restaurant.domain.dto.response.restaurant.one;

import lombok.Data;

/**
 * dto для главной страницы ресторана
 */
@Data
public class IngredientDTO {
    private String title;
    private int weight;
}
