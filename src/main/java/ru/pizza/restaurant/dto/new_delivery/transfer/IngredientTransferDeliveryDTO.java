package ru.pizza.restaurant.dto.new_delivery.transfer;

import lombok.Data;

@Data
public class IngredientTransferDeliveryDTO {
    private String title;
    private int weight;
    private boolean isNew;
}
