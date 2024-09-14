package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.restaurant.BuildingDAO;
import ru.pizza.restaurant.domain.entities.Building;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestBuildingService {
    private final BuildingDAO buildingDAO;

    /**
     * @return Возвращает все рестораны
     */
    public List<Building> findAll() {
        return buildingDAO.findAll();
    }

    /**
     * @param id ID ресторана
     * @return Возвращает определенный ресторан
     */
    public Building findById(Integer id) {
        return buildingDAO.findById(id);
    }
}