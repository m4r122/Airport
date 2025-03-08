package com.unimagdalena.airport.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Aerolineas")
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nombre;


    @ManyToMany
    @JoinTable(
            name = "aerolineasVuelos",
            joinColumns = @JoinColumn(name = "aerolineas_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vuelos_id", nullable = true, referencedColumnName = "id"))
    private Set<Vuelo> vuelos;
}
