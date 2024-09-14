package ru.pizza.restaurant.services.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.pizza.restaurant.domain.dto.response.restaurant.one.BuildingDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BuildingService {
    private final RestTemplate restTemplate;

    public List<BuildingDTO> index() {
        return List.of(restTemplate.getForObject("http://RESTAURANT/restaurant/api/buildings", BuildingDTO[].class));
    }
    public BuildingDTO findById(Integer id) {
        return restTemplate.getForObject("http://RESTAURANT/restaurant/api/buildings/" + id, BuildingDTO.class);
    }
}
