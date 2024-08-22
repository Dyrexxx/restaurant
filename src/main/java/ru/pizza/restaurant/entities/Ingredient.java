package ru.pizza.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Ingredient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "weight")
    private int weight;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    @Transient
    private boolean isNew;
}