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
    /**
     * {@link OrderBasketController#orderBasketService}
     * сервис для работы с корзиной онлайн-заказов из мкс Site
     */
    private final OrderBasketService orderBasketService;

    /**
     *
     * @param buildingId ID ресторана
     * @param model передает объекты в thymeleaf
     * @return html страница онлайн-заказов
     */
    @GetMapping("/orders/{buildingId}")
    public String index(@PathVariable Integer buildingId, Model model) {
        model.addAttribute("ordersList", orderBasketService.index(buildingId));
        return "orders";
    }
}
