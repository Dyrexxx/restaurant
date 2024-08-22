package ru.pizza.restaurant.dto.order_basket;

import lombok.Data;

import java.util.Objects;

@Data
public class OrderBasketContentDTO {
    private String fio;
    private String address;
    private boolean isReady = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBasketContentDTO that = (OrderBasketContentDTO) o;
        return Objects.equals(fio, that.fio) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, address);
    }
}
