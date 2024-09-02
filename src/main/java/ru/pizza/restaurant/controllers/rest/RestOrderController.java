package ru.pizza.restaurant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.services.rest.RestOrderBasketService;

import java.util.List;

@RestController
@RequestMapping("/restaurant/api")
@RequiredArgsConstructor
public class RestOrderController {
    private final RestOrderBasketService orderBasketService;

    /***
     *
     * @return список всех заказов онлайн-покупателей
     */
    @GetMapping("/orders")
    public List<BasketOrderDTO> index() {
        return orderBasketService.index();
    }

    /**
     * @param buildingId id определенного ресторана
     * @return все онлайн-заказы определенного ресторана
     */
    @GetMapping("/orders/{buildingId}")
    public List<BasketOrderDTO> index(@PathVariable Integer buildingId) {
        return orderBasketService.index(buildingId);
    }

    /***
     *
     */
    @PatchMapping("/orders/{buildingId}/{orderId}")
    public void prepareOrder(@PathVariable int buildingId, @PathVariable String orderId) {
        orderBasketService.update(buildingId, orderId);
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
