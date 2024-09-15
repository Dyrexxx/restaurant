package ru.pizza.restaurant.services.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.pizza.restaurant.domain.dto.response.restaurant.all.BuildingWithoutWarehouseForAllBuildingListDTO;
import ru.pizza.restaurant.domain.dto.response.restaurant.one.BuildingDTO;
import ru.pizza.restaurant.exceptions.NoContentException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BuildingService {
    private final RestTemplate restTemplate;

    public List<BuildingWithoutWarehouseForAllBuildingListDTO> index() {
        BuildingWithoutWarehouseForAllBuildingListDTO[] buildingDTOS = restTemplate.getForObject("http://RESTAURANT/restaurant/api/buildings", BuildingWithoutWarehouseForAllBuildingListDTO[].class);
        if (buildingDTOS == null || buildingDTOS.length == 0) {
            throw new NoContentException("index");
        }
        return List.of(buildingDTOS);
    }

    public BuildingDTO findById(Integer id) {
        BuildingDTO buildingDTO = restTemplate.getForObject("http://RESTAURANT/restaurant/api/buildings/" + id, BuildingDTO.class);
        if (buildingDTO == null || buildingDTO.getIngredientList() == null || buildingDTO.getIngredientList().isEmpty()) {
            throw new NoContentException("building", buildingDTO != null ? buildingDTO.getId() : 0);
        }
        return buildingDTO;
    }
}