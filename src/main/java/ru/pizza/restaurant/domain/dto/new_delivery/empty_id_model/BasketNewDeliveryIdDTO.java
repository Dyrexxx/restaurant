package ru.pizza.restaurant.domain.dto.new_delivery.empty_id_model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/***
 * dto для получения ID в thymeleaf
 */
@Data
@Schema(description = "dto для получения ID в thymeleaf")
public class BasketNewDeliveryIdDTO {
    @Schema(description = "ID корзины доставки")
    private String id;
}

