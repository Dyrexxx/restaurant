package ru.pizza.restaurant.services.mvc;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.pizza.restaurant.domain.entities.Building;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BuildingService {
    private final RestTemplate restTemplate;

    public List<Building> index() {
        return List.of(restTemplate.getForObject("http://RESTAURANT/restaurant/api/buildings", Building[].class));
    }
    public Building findById(Integer id) {
        return restTemplate.getForObject("http://RESTAURANT/restaurant/api/buildings/" + id, Building.class);
    }
}
