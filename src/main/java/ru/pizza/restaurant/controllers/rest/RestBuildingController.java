package ru.pizza.restaurant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.services.rest.RestBuildingService;

import java.util.List;

@RestController
@RequestMapping("restaurant/api")
@RequiredArgsConstructor
public class RestBuildingController {
    private final RestBuildingService restBuildingService;

    /***
     *
     * @return возвращает список всех действующих ресторанов с их складами
     */
    @GetMapping("/buildings")
    public List<Building> index() {
        return restBuildingService.findAll();
    }


    /***
     *
     * @param id ID ресторана
     * @return возвращает определенный ресторан
     */
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
    @PatchMapping("/buildings")
    public void updateOrSave(@RequestBody List<Building> buildings) {
        restBuildingService.updateOrSave(buildings);
    }
}