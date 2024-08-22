package ru.pizza.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.NewDeliveryBasketDAO;
import ru.pizza.restaurant.dto.new_delivery.Basket;
import ru.pizza.restaurant.dto.new_delivery.IngredientDTO;

import java.util.List;
import java.util.Map;

@Service
public class NewDeliveryService {
    private final NewDeliveryBasketDAO newDeliveryBasketDAO;

    @Autowired
    public NewDeliveryService(NewDeliveryBasketDAO newDeliveryBasketDAO) {
        this.newDeliveryBasketDAO = newDeliveryBasketDAO;
    }

    public Map<Basket, List<IngredientDTO>> getNewDelivery(int id) {
       return newDeliveryBasketDAO.findAllById(id);

    }


    @Transactional
    public void accept(String basketId, int buildingId) {
        newDeliveryBasketDAO.accept(basketId, buildingId);
    }
}