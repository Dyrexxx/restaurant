package ru.pizza.restaurant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.services.rest.RestNewDeliveryService;

import java.util.List;

@RestController
@RequestMapping("/restaurant/api")
@RequiredArgsConstructor
public class RestNewDeliveryController {
    private final RestNewDeliveryService restNewDeliveryService;

    @GetMapping("/deliveries/{buildingId}")
    public List<BasketDeliveryDTO> findByBuildingId(@PathVariable int buildingId) {
        return restNewDeliveryService.index(buildingId);
    }

    /***
     * Когда доставка приезжает в ресторан, то обновляет склад
     *
     * @param buildingId ID ресторана
     * @param basketId ID корзины
     * @return переадресация на страницу ресторана
     */
    @PostMapping("/deliveries/{buildingId}/{basketId}")
    public String acceptDelivery(@PathVariable int buildingId,
                                 @PathVariable String basketId) {
        restNewDeliveryService.update(buildingId, basketId);
        return "redirect:/restaurant/" + buildingId;
    }
}
