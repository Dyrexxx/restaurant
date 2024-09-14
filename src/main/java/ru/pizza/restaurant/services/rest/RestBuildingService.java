package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.restaurant.BuildingDAO;
import ru.pizza.restaurant.domain.dto.response.restaurant.all.BuildingWithoutWarehouseForAllBuildingListDTO;
import ru.pizza.restaurant.domain.dto.response.restaurant.one.BuildingDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestBuildingService {
    private final BuildingDAO buildingDAO;

    /**
     * @return Возвращает все рестораны
     */
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<BuildingWithoutWarehouseForAllBuildingListDTO> findAll() {
        return buildingDAO.findAll();
    }

    /**
     * @param id ID ресторана
     * @return Возвращает определенный ресторан
     */
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public BuildingDTO findById(Integer id) {
        return buildingDAO.findById(id);
    }
}