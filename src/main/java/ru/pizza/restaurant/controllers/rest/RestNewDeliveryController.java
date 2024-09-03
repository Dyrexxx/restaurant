package ru.pizza.restaurant.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.services.rest.RestNewDeliveryService;

import java.util.List;

@RestController
@RequestMapping("/restaurant/api")
@Tag(name = "RestNewDeliveryController", description = "API для работы с доставкой")
@RequiredArgsConstructor
public class RestNewDeliveryController {
    private final RestNewDeliveryService restNewDeliveryService;

    /***
     *
     * @param buildingId ID нужного ресторана
     * @return Возвращает все доставки, которые в пути, для нужного ресторана
     */
    @Operation(summary = "Доставки, которые в пути, для определенного ресторана")
    @ApiResponse(responseCode = "200", description = "Корзина текущих доставок получена")
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
    @Operation(summary = "Доставка приехала: пополнить склад")
    @ApiResponse(responseCode = "200", description = "Доставка успешно получена")
    @PostMapping("/deliveries/{buildingId}/{basketId}")
    public String acceptDelivery(@PathVariable int buildingId,
                                 @PathVariable String basketId) {
        restNewDeliveryService.update(buildingId, basketId);
        return "redirect:/restaurant/" + buildingId;
    }
}
