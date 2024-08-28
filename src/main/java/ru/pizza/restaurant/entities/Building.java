package ru.pizza.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "building")
public class Building {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "is_new_delivery")
    private boolean isNewDelivery;
    @OneToMany(mappedBy = "building")
    @JsonIdentityReference
    private List<Ingredient> ingredientList;
}
