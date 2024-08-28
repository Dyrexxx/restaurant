package ru.pizza.restaurant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.entities.Ingredient;
import ru.pizza.restaurant.services.BuildingService;
import ru.pizza.restaurant.services.WarehouseService;
import ru.pizza.restaurant.services.OrderBasketService;

import java.util.List;

@RestController
@RequestMapping("/dodo")
@RequiredArgsConstructor
public class DodoRestController {
    private final BuildingService buildingService;
    private final WarehouseService ingredientService;
    private final OrderBasketService orderBasketService;


    @GetMapping("/buildings")
    public List<Building> indexBuilding() {
        return buildingService.findAll();
    }

    @GetMapping("/ingredients")
    public List<Ingredient> indexIngredient() {
        return ingredientService.findAll();
    }

    @PostMapping("/new-delivery")
    @ResponseBody
    public void delivery(@RequestBody List<Building> buildings) {
        buildingService.save(buildings);
    }

    @PostMapping("/newOrder")
    @ResponseBody
    public void newOrder(@RequestBody BasketOrderDTO basketOrderDTO) {
        orderBasketService.save(basketOrderDTO);
    }

    @GetMapping("/get-orders")
    public List<BasketOrderDTO> getOrders() {
        return orderBasketService.findAll();
    }
}