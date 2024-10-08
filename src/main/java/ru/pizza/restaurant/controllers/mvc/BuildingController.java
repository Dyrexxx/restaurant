package ru.pizza.restaurant.controllers.mvc;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pizza.restaurant.services.mvc.BuildingService;

@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class BuildingController {
    /**
     * {@link BuildingController#buildingService}
     * сервис для ресторанов
     */
    private final BuildingService buildingService;

    /**
     *
     * @param model объекты в thymeleaf
     * @return html страницу
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("buildingList", buildingService.index());
        return "index";
    }

    /**
     *
     * @param buildingId ID ресторана
     * @param model объекты в thymeleaf
     * @return html страницу
     */
    @GetMapping("/{buildingId}")
    public String getBuilding(@PathVariable int buildingId, Model model) {
        model.addAttribute("building", buildingService.findById(buildingId));
        return "building";
    }

}
