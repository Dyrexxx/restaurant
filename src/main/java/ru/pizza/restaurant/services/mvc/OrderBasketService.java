package ru.pizza.restaurant.services.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.services.BaseMethodsBasketService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderBasketService implements BaseMethodsBasketService<BasketOrderDTO, Integer> {
    private final RestTemplate restTemplate;

    @Override
    public List<BasketOrderDTO> index(Integer id) {
        return List.of(restTemplate.getForObject("http://RESTAURANT/restaurant/api/orders/{id}", BasketOrderDTO[].class, id));
    }
}
