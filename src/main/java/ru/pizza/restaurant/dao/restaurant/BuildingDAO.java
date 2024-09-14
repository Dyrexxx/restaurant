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
    /**
     * {@link BuildingDAO#buildingRepository}
     * репозиторий для ресторанов
     */
    private final BuildingRepository buildingRepository;

    /**
     *
     * @return все рестораны
     */
    public List<Building> findAll() {
        List<Building> buildingList = buildingRepository.findAll();
        if (buildingList.isEmpty()) {
            throw new NoSuchElementException("Рестораны не найдены");
        }
        return buildingList;
    }

    /**
     *
     * @param id ID ресторана
     * @return определенный ресторан
     */
    public Building findById(Integer id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ресторан не найден"));
    }
}
