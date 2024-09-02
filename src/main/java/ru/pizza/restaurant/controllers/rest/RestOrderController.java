package ru.pizza.restaurant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.services.rest.RestOrderBasketService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class RestOrderController {
    private final RestOrderBasketService orderBasketService;

    /***
     *
     * @return список всех заказов онлайн-покупателей
     */
    @GetMapping
    public List<BasketOrderDTO> index() {
        return orderBasketService.findAll();
    }

    /***
     * Получает заказ онлайн-покупателя и добавляет в очередь для дальнейшего приготовления
     *
     * @param basketOrderDTO конечная корзина онлайн-покупателя
     */
    @PostMapping
    public void save(@RequestBody BasketOrderDTO basketOrderDTO) {
        orderBasketService.save(basketOrderDTO);
    }
}
