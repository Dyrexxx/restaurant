package ru.pizza.restaurant.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.services.rest.RestWarehouseService;

import java.util.List;

@RestController
@RequestMapping("restaurant/api")
@Tag(name = "RestIngredientController", description = "API для работы со складами")
@RequiredArgsConstructor
public class RestIngredientController {
    private final RestWarehouseService ingredientService;

    /***
     *
     * @return Возвращает список всех складов
     */
    @Operation(summary = "Возвращает склады всех ресторанов")
    @ApiResponse(responseCode = "200", description = "Все склады получены")
    @GetMapping("/ingredients")
    public List<Ingredient> index() {
        return ingredientService.index();
    }

}
