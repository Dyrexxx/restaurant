package ru.pizza.restaurant.domain.dto.order_basket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Schema(description = "dto для работы с корзиной онлайн-заказов")
public class BasketOrderDTO {
    @Schema(description = "ID корзины")
    private String id;
    @Schema(description = "ФИО заказчика")
    private String fio;
    @Schema(description = "Адрес куда нужно доставить")
    private String address;
    @Schema(description = "Списток продукции в заказе")
    private List<ProductOrderDTO> productsList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketOrderDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}