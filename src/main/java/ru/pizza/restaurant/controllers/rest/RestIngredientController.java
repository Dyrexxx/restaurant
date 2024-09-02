package ru.pizza.restaurant.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.services.rest.RestWarehouseService;

import java.util.List;

@RestController
@RequestMapping("restaurant/api")
@RequiredArgsConstructor
public class RestIngredientController {
    private final RestWarehouseService ingredientService;

    /***
     *
     * @return Возвращает список всех складов
     */
    @GetMapping("/ingredients")
    public List<Ingredient> index() {
        return ingredientService.index();
    }

}
