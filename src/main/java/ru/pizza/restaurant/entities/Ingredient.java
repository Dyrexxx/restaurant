package ru.pizza.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "warehouse")
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