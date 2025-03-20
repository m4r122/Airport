package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    Pasajero createPasajero(Pasajero pasajero);
    Optional<Pasajero> getPasajeroById(Long id);
    List<Pasajero> getAllPasajeros();
    Pasajero updatePasajero(Long id, Pasajero updatedPasajero);
    void deletePasajero(Long id);
}
