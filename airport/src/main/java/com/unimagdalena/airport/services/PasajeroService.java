package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Pasaporte;
import com.unimagdalena.airport.repositories.PasajeroRepository;
import com.unimagdalena.airport.entities.Pasajero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class PasajeroService {
    private final PasajeroRepository pasajeroRepository;


    @Autowired
    public PasajeroService(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }


    public Pasajero createPasajero(Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }


    public Optional<Pasajero> getPasajeroById(Long id) {
        return pasajeroRepository.findById(id);
    }


    public List<Pasajero> getAllPasajeros() {
        return pasajeroRepository.findAll();
    }


    public Pasajero updatePasajero(Long id, Pasajero updatedPasajero) {
        return pasajeroRepository.findById(id).map(pasajero -> {
            pasajero.setNombre(updatedPasajero.getNombre());
            pasajero.setNID(updatedPasajero.getNID());
            pasajero.setPasaporte(updatedPasajero.getPasaporte());
            return pasajeroRepository.save(pasajero);
        }).orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));
    }


    public void deletePasajero(Long id) {
        pasajeroRepository.deleteById(id);
    }
}
