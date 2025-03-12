package com.unimagdalena.airport.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private UUID numeroVuelo;
    @Column(nullable = false)
    private String origen;
    @Column(nullable = false)
    private String destino;

    @ManyToMany(mappedBy = "vuelos")
    private Set<Aerolinea> aerolineas;

    @OneToMany(mappedBy = "vuelo")
    private Set<Reserva> reservas;


}