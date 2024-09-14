package ru.pizza.restaurant.dao.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.repositories.BuildingRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuildingDAO {
    private final BuildingRepository buildingRepository;

    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    public Building findById(Integer id) {
        return buildingRepository.findById(id).orElse(null);
    }

}
