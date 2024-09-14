package ru.pizza.restaurant.domain.dto.response.restaurant.one;

import lombok.Data;
import ru.pizza.restaurant.domain.entities.Ingredient;

import java.util.List;

@Data
public class BuildingDTO {
    private int id;
    private String title;
    private String address;
    private List<IngredientDTO> ingredientList;
}
