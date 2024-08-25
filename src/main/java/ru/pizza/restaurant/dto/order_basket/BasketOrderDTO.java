package ru.pizza.restaurant.dto.order_basket;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BasketOrderDTO {
    private String fio;
    private String address;
    private List<ProductOrderDTO> productOrderDTOList = new ArrayList<>();
}