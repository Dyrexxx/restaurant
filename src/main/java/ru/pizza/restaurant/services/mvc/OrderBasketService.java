package ru.pizza.restaurant.services.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.pizza.restaurant.domain.dto.response.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.exceptions.NoContentException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderBasketService {
    private final RestTemplate restTemplate;

    public List<BasketOrderDTO> index(Integer id) {
        BasketOrderDTO[] basketOrderDTOS = restTemplate.getForObject("http://RESTAURANT/restaurant/api/orders/{id}", BasketOrderDTO[].class, id);
        if (basketOrderDTOS == null || basketOrderDTOS.length == 0) {
            throw new NoContentException("orders");
        }
        return List.of(basketOrderDTOS);
    }
}
