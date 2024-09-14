package ru.pizza.restaurant.domain.dto.response.restaurant.one;

import lombok.Data;

import java.util.List;

/**
 * dto для главной страницы ресторана
 */
@Data
public class BuildingDTO {
    private int id;
    private String title;
    private String address;
    private List<IngredientDTO> ingredientList;
}
