package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Pasajero;
import com.unimagdalena.airport.entities.Pasaporte;

import java.util.List;
import java.util.Optional;

public interface PasaporteService {
    Pasaporte createPasaporte(Pasaporte pasaporte);
    Optional<Pasaporte> getPasaporteById(Long id);
    List<Pasaporte> getAllPasaportes();
    Pasaporte updatePasaporte(Long id, Pasaporte updatedPasaporte);
    void deletePasaporte(Long id);
}
