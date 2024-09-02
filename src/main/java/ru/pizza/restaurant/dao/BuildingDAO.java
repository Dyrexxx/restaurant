package ru.pizza.restaurant.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.impl.BaseMethodsDB;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.repositories.BuildingRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuildingDAO implements BaseMethodsDB<Building, Integer> {
    private final BuildingRepository buildingRepository;

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Building findById(Integer id) {
        return buildingRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Building building) {

    }


    @Override
    public void deleteById(Integer id) {

    }
}
