package ru.pizza.restaurant.controllers.parent;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.pizza.restaurant.services.BuildingService;

@Controller
@Getter
@Setter
public abstract class AbstractController {
    protected final BuildingService buildingService;


    @Autowired
    public AbstractController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }


}