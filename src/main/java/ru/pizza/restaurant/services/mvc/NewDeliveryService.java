package ru.pizza.restaurant.services.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.pizza.restaurant.domain.dto.new_delivery.base.BasketDeliveryDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewDeliveryService {
    private final RestTemplate restTemplate;

    public List<BasketDeliveryDTO> index(int buildingId) {
        String url = "http://RESTAURANT/restaurant/api/deliveries/" + buildingId;
        return List.of(restTemplate.getForObject(url, BasketDeliveryDTO[].class));
    }
}
