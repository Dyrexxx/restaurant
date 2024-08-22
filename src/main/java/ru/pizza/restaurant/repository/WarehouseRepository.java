package ru.pizza.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pizza.restaurant.entities.Ingredient;

@Repository
public interface WarehouseRepository extends JpaRepository<Ingredient, Integer> {
}
