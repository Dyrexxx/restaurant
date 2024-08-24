package ru.pizza.restaurant.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dao.parent.BaseOperationDB;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.repository.BuildingRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuildingDAO implements BaseOperationDB<Building, Integer> {
    private final BuildingRepository buildingRepository;

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Building findOneById(Integer id) {
        return buildingRepository.findById(id).orElse(null);
    }
}
