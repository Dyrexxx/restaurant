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
     * @return возвращает все рестораны
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
     * @return возвращает определенный ресторан по ID
     */
    @Operation(summary = "Найти ресторан", description = "Находит 1 ресторан по ID")
    @ApiResponse(responseCode="200", description = "Ресторан найден")
    @GetMapping("/buildings/{id}")
    public Building findById(@PathVariable int id) {
        return restBuildingService.findById(id);
    }

}