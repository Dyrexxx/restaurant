package ru.pizza.restaurant.domain.dto.new_delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class IngredientDeliveryDTO {
    @Schema(description = "Название ингредиента")
    private String title;
    @Schema(description = "граммовка")
    private int weight;
    @Schema(description = "Проверка. Содержится ли ингредиент на сладе или нет")
    private boolean isNew;
}
