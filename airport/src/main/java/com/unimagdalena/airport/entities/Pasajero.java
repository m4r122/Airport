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
@Table(name = "Pasajeros")
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String NID;

    @OneToOne

    @JoinColumn(name = "pasaporte_id")
    private Pasaporte pasaporte;

    @OneToMany(mappedBy = "pasajero")
    private Set<Reserva> reservas;
}

