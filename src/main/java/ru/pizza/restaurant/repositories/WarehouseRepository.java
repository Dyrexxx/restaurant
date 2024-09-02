package ru.pizza.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pizza.restaurant.domain.entities.Ingredient;

@Repository
public interface WarehouseRepository extends JpaRepository<Ingredient, Integer> {
}
