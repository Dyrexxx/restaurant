package ru.pizza.restaurant.domain.dto.response.new_delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/***
 * {@link BasketDeliveryDTO}
 * dto для работы с корзиной доставки
 */
@Data
@Schema(description = "dto для работы с корзиной доставки")
public class BasketDeliveryDTO {
    @Schema(description = "ID корзины")
    private String id;
    @Schema(description = "list ингредиентов доставки")
    private List<IngredientDeliveryDTO> ingredientList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketDeliveryDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}