package ru.pizza.restaurant.services;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.BuildingDAO;
import ru.pizza.restaurant.dao.new_delivery.NewDeliveryBasketDAO;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.global_parent.BaseMethodsDB;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingService implements BaseMethodsDB<Building, Integer> {
    private final BuildingDAO buildingDAO;
    private final NewDeliveryBasketDAO newDeliveryBasketDAO;

    @Override
    public List<Building> findAll() {
        return buildingDAO.findAll();
    }


    @Override
    public Building findById(Integer id) {
        return buildingDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Building building) {

    }
    @Transactional
    public void save(List<Building> buildings) {
        newDeliveryBasketDAO.save(buildings);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {

    }
}