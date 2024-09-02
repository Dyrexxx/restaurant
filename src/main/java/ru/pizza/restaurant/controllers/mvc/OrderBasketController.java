package ru.pizza.restaurant.controllers.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.services.mvc.OrderBasketService;

@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class OrderBasketController {
    private final OrderBasketService orderBasketService;

    @GetMapping("/orders/{buildingId}")
    public String index(@PathVariable Integer buildingId, Model model) {
        model.addAttribute("ordersList", orderBasketService.index(buildingId));
        return "orders";
    }
}
