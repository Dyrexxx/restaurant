package ru.pizza.restaurant.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.BuildingDAO;
import ru.pizza.restaurant.dao.NewDeliveryBasketDAO;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.repository.BuildingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingService {
    private final BuildingDAO buildingDAO;
    private final NewDeliveryBasketDAO newDeliveryBasketDAO;


    public List<Building> index() {
        return buildingDAO.findAll();
    }

    public Building get(int id) {
        return buildingDAO.findOneById(id);
    }


    @Transactional
    public void receiveNewDelivery(List<Building> buildings) {
        newDeliveryBasketDAO.receive(buildings);
    }
}