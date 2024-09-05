package ru.pizza.restaurant.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.services.rest.RestOrderBasketService;

import java.util.List;

@RestController
@RequestMapping("/restaurant/api")
@Tag(name="RestOrderController", description = "API для работы с онлайн-заказами")
@RequiredArgsConstructor
public class RestOrderController {
    private final RestOrderBasketService orderBasketService;

    /***
     *
     * @return список всех заказов онлайн-покупателей
     */
    @Operation(summary = "Получить все онлайн-заказы во всех ресторанах")
    @ApiResponse(responseCode = "200", description = "Онлайн-заказы получены")
    @GetMapping("/orders")
    public List<BasketOrderDTO> index() {
        return orderBasketService.index();
    }

    /**
     * @param buildingId id определенного ресторана
     * @return все онлайн-заказы определенного ресторана
     */
    @Operation(summary = "Получить онлайн-заказы для определенного ресторана")
    @ApiResponse(responseCode = "200", description = "Онлайн-заказы получены")
    @GetMapping("/orders/{buildingId}")
    public List<BasketOrderDTO> index(@PathVariable Integer buildingId) {
        return orderBasketService.index(buildingId);
    }

    /***
     * Подтвердить, что онлайн-заказ обработан и нуждается в доставке
     * @param buildingId ID ресторана
     * @param orderId ID заказа
     */
    @Operation(summary = "Ресторан подтверждает, что заказ готов и нуждается в доставке")
    @ApiResponse(responseCode = "200", description = "Заказ обработан")
    @PatchMapping("/orders/{buildingId}/{orderId}")
    public void prepareOrder(@PathVariable int buildingId, @PathVariable String orderId) {
        orderBasketService.update(buildingId, orderId);
    }

    /***
     * Получает заказ онлайн-покупателя и добавляет в очередь для дальнейшего приготовления
     *
     * @param basketOrderDTO конечная корзина онлайн-покупателя
     */
    @Operation(summary = "Сохраняет онлайн-заказ и добавляет в очередь для приготовления")
    @ApiResponse(responseCode = "200", description = "Онлайн-заказ сохранен")
    @PostMapping("/orders")
    public void save(@RequestBody BasketOrderDTO basketOrderDTO) {
        orderBasketService.save(basketOrderDTO);
    }
}
