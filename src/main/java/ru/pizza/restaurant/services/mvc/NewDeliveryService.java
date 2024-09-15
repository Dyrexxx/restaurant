package ru.pizza.restaurant.services.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.pizza.restaurant.domain.dto.response.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.exceptions.NoContentException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewDeliveryService {
    private final RestTemplate restTemplate;

    public List<BasketDeliveryDTO> index(int buildingId) {
        String url = "http://RESTAURANT/restaurant/api/deliveries/" + buildingId;
        BasketDeliveryDTO[] basketDeliveryDTOS = restTemplate.getForObject(url, BasketDeliveryDTO[].class);
        if (basketDeliveryDTOS == null || basketDeliveryDTOS.length == 0) {
            throw new NoContentException("new_delivery");
        }
        return List.of(basketDeliveryDTOS);
    }
}
