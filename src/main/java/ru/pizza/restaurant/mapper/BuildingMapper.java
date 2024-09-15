package ru.pizza.restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pizza.restaurant.domain.dto.response.restaurant.all.BuildingWithoutWarehouseForAllBuildingListDTO;
import ru.pizza.restaurant.domain.dto.response.restaurant.one.BuildingDTO;
import ru.pizza.restaurant.domain.entities.Building;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BuildingMapper {
    List<BuildingDTO> toBuildingDTOList(List<Building> buildingList);
    BuildingDTO toBuildingDTO(Building building);
}
