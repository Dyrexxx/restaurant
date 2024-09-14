package ru.pizza.restaurant.domain.dto.response.new_delivery.thymeleaf;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/***
 * {@link BasketNewDeliveryIdDTO}
 * dto для получения ID в thymeleaf
 */
@Data
@Schema(description = "dto для получения ID в thymeleaf")
public class BasketNewDeliveryIdDTO {
    @Schema(description = "ID корзины доставки")
    private String id;
}

