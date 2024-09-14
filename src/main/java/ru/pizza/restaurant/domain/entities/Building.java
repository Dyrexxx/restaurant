package ru.pizza.restaurant.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * сущность для работы с ресторанами
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "building")
@Schema(description = "сущность для работы с ресторанами")
public class Building {
    @Id
    @Column(name = "id")
    @Schema(description = "ID ресторана")
    private int id;

    @Column(name = "title")
    @Schema(description = "Название ресторана")
    private String title;

    @Column(name = "is_new_delivery")
    @Schema(description = "Проверка. Есть ли новые доставки в ресторан")
    private boolean isNewDelivery;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIdentityReference
    @Schema(description = "list склад ресторана")
    private List<Ingredient> ingredientList;
}
