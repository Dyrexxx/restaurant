package ru.pizza.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pizza.restaurant.entities.Building;

@Repository
public interface BuildingRepository  extends JpaRepository<Building, Integer> {
}
