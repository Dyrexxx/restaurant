package ru.pizza.restaurant.dao.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.dto.response.restaurant.all.BuildingWithoutWarehouseForAllBuildingListDTO;
import ru.pizza.restaurant.domain.dto.response.restaurant.one.BuildingDTO;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.mapper.BuildingMapper;
import ru.pizza.restaurant.repositories.BuildingRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class BuildingDAO {
    private final BuildingRepository buildingRepository;

    public List<Building> findAll() {
        List<Building> buildingList = buildingRepository.findAll();
        if (buildingList.isEmpty()) {
            throw new NoSuchElementException("Рестораны не найдены");
        }
        return buildingList;
    }

    public Building findById(Integer id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ресторан не найден"));
    }
}
