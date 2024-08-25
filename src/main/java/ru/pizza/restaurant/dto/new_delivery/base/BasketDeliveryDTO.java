package ru.pizza.restaurant.dto.new_delivery.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BasketDeliveryDTO {
    private String id;
    private List<IngredientDeliveryDTO> ingredientList = new ArrayList<>();
}
