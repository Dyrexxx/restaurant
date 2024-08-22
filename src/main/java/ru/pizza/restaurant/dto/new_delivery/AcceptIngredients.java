package ru.pizza.restaurant.dto.new_delivery;

import lombok.Data;

@Data
public class AcceptIngredients extends IngredientDTO {
    private boolean isNew;
}
