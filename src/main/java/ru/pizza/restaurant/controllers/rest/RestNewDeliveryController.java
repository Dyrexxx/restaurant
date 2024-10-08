package ru.pizza.restaurant.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.response.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.NewDeliveryDTO;
import ru.pizza.restaurant.services.rest.RestNewDeliveryService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurant/api")
@Tag(name = "RestNewDeliveryController", description = "API для работы с доставкой")
@RequiredArgsConstructor
public class RestNewDeliveryController {
    /**
     * {@link RestNewDeliveryController#restNewDeliveryService}
     * сервис для работы в контроллере доставок из main-warehouse
     */
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
     */
    @Operation(summary = "Доставка приехала: пополнить склад")
    @ApiResponse(responseCode = "200", description = "Доставка успешно получена")
    @PatchMapping("/deliveries/{buildingId}/{basketId}")
    public void acceptDelivery(
            HttpServletResponse response,
            @PathVariable int buildingId,
            @PathVariable String basketId) throws IOException {
        restNewDeliveryService.update(buildingId, basketId);
        response.sendRedirect("http://localhost:8085/restaurant/" + buildingId);
    }


    /***
     * Сохраняет в базу данных ингредиенты, которые должны в скором времени приехать в рестораны
     *
     * @param newDeliveryList корзина новых ингредиентов
     */
    @Operation(summary = "Сохраняет в бд доставки список ингредиентов, которые уже в пути в ресторан",
            description = "Ожидание новых доставок")
    @ApiResponse(responseCode = "200", description = "Доставка в пути")
    @PostMapping("/deliveries")
    public void expectationNewDelivery(@RequestBody List<NewDeliveryDTO> newDeliveryList) {
        restNewDeliveryService.save(newDeliveryList);
    }
}
