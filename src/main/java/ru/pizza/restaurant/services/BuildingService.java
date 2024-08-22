package ru.pizza.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.NewDeliveryBasketDAO;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.repository.BuildingRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final NewDeliveryBasketDAO newDeliveryBasketDAO;


    @Autowired
    public BuildingService(BuildingRepository buildingRepository, NewDeliveryBasketDAO newDeliveryBasketDAO) {
        this.buildingRepository = buildingRepository;
        this.newDeliveryBasketDAO = newDeliveryBasketDAO;
    }

    public List<Building> index() {
        return buildingRepository.findAll();
    }

    public Building get(int id) {
        return buildingRepository.findById(id).orElse(null);
    }


    @Transactional
    public void receiveNewDelivery(List<Building> buildings) {
        newDeliveryBasketDAO.receive(buildings);
    }
}