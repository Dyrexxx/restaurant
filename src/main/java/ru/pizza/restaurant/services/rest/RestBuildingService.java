package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.restaurant.BuildingDAO;
import ru.pizza.restaurant.domain.dto.response.restaurant.one.BuildingDTO;
import ru.pizza.restaurant.mapper.BuildingMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestBuildingService {
    private final BuildingDAO buildingDAO;
    private final BuildingMapper buildingMapper;

    /**
     * @return Возвращает все рестораны
     */
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<BuildingDTO> findAll() {
        return buildingMapper.toBuildingDTOList(buildingDAO.findAll());
    }

    /**
     * @param id ID ресторана
     * @return Возвращает определенный ресторан
     */
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public BuildingDTO findById(Integer id) {
        return buildingMapper.toBuildingDTO(buildingDAO.findById(id));
    }
}