package ru.pizza.restaurant.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.services.rest.RestBuildingService;

import java.util.List;

@RestController
@RequestMapping("restaurant/api")
@Tag(name = "RestBuildingController", description = "API для работы с ресторанами")
@RequiredArgsConstructor
public class RestBuildingController {

    private final RestBuildingService restBuildingService;

    /***
     *
     * @return возвращает список всех действующих ресторанов с их складами
     */
    @Operation(summary = "Найти все рестораны", description = "Находит все рестораны")
    @ApiResponse(responseCode="200", description = "Все рестораны найдены")
    @GetMapping("/buildings")
    public List<Building> index() {
        return restBuildingService.findAll();
    }

    /***
     *
     * @param id ID ресторана
     * @return возвращает определенный ресторан
     */
    @Operation(summary = "Найти ресторан", description = "Находит 1 ресторан по ID")
    @ApiResponse(responseCode="200", description = "Ресторан найден")
    @GetMapping("/buildings/{id}")
    public Building findById(@PathVariable int id) {
        return restBuildingService.findById(id);
    }

    /***
     * Получает список ресторанов со списком новых ингредиентов, которые обновят текущее количество запасов на складе.
     * Если какого-то ингредиента нет на складе, то он добавляется. Если есть, то обновляется количество
     *
     * @param buildings рестораны в которые привезут пополнение запасов
     */
    @Operation(summary = "Пополнить запасы ресторанов",
            description = "Пополняет запасы складов нужных ресторанов, где были сформированны доставки из main-warehouse")
    @ApiResponse(responseCode="200", description = "Доставка в пути")
    @PatchMapping("/buildings")
    public void waitingForDelivery(@RequestBody List<Building> buildings) {
        restBuildingService.updateOrSave(buildings);
    }
}