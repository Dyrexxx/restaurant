package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.new_delivery.NewDeliveryBasketDAO;
import ru.pizza.restaurant.domain.dto.new_delivery.base.BasketDeliveryDTO;
import ru.pizza.restaurant.impl.BasketMethodsDB;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestNewDeliveryService implements BasketMethodsDB<BasketDeliveryDTO, String, Integer> {
    private final NewDeliveryBasketDAO newDeliveryBasketDAO;

    @Override
    public List<BasketDeliveryDTO> findAll(Integer id) {
        return newDeliveryBasketDAO.findAll(id);
    }
    @Override
    public List<BasketDeliveryDTO> findAll() {
        return List.of();
    }

    @Override
    public BasketDeliveryDTO findById(String id) {
        return null;
    }

    @Override
    @Transactional
    public void update(int buildingId, String id) {
        newDeliveryBasketDAO.update(buildingId, id);
    }

    @Override
    @Transactional
    public void save(BasketDeliveryDTO basketDeliveryDTO) {

    }

    @Override
    @Transactional
    public void deleteById(String id) {

    }
}