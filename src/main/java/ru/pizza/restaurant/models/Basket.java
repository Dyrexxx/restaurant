package ru.pizza.restaurant.models;

import lombok.Data;
import ru.pizza.restaurant.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

//TODO ИЗМЕНИТЬ
@Data
public class Basket {
    private String id;
    private String fio;
    private String address;
    private boolean isReadyEnum;
    private List<BasketItem> basketViewItemList = new ArrayList<>();

    @Data
    public static class BasketItem {
        private String id;
        private int buildingId;
        private String productName;
        private int price;
        private int count;
        private List<Ingredient> ingredients;
    }
}
