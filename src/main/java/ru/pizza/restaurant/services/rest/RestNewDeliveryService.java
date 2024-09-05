package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.new_delivery.NewDeliveryBasketDAO;
import ru.pizza.restaurant.domain.dto.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.NewDeliveryDTO;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.services.BaseMethodsBasketService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestNewDeliveryService implements BaseMethodsBasketService<BasketDeliveryDTO, Integer> {
    private final NewDeliveryBasketDAO newDeliveryBasketDAO;

    @Override
    public List<BasketDeliveryDTO> index(Integer id) {
        return newDeliveryBasketDAO.findAll(id);
    }

    @Transactional
    public void update(int buildingId, String id) {
        newDeliveryBasketDAO.update(buildingId, id);
    }


    @Transactional
    public void save(NewDeliveryDTO newDelivery) {
        newDeliveryBasketDAO.save(newDelivery);
    }
}