package ru.pizza.restaurant.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "warehouse")
public class Ingredient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID ингредиента")
    private int id;

    @Column(name = "title")
    @Schema(description = "Название ингредиента")
    private String title;

    @Column(name = "weight")
    @Schema(description = "граммовка")
    private int weight;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @Schema(description = "ID ресторана")
    private Building building;

    @Transient
    private boolean isNew;
}