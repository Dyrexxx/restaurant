package ru.pizza.restaurant.dao.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.repositories.BuildingRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuildingDAO {
    private final BuildingRepository buildingRepository;

    public List<Building> findAll() {
        List<Building> buildings = buildingRepository.findAll();
        if (buildings.isEmpty()) {
            throw new NoSuchElementException("Рестораны не найдены");
        }
        return buildings;
    }

    public Building findById(Integer id) {
        return buildingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ресторан не найден"));
    }
}
