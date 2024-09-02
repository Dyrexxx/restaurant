package ru.pizza.restaurant.domain.dto.new_delivery.base;

import lombok.Data;

@Data
public class IngredientDeliveryDTO {
    private String title;
    private int weight;
    private boolean isNew;
}
