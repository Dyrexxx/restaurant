package ru.pizza.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pizza.restaurant.entities.Building;

public interface BuildingRepository  extends JpaRepository<Building, Integer> {
}
