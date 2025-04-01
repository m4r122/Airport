package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Pasajero;
import com.unimagdalena.airport.entities.Pasaporte;
import com.unimagdalena.airport.repositories.PasaporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasaporteServiceImpl implements PasaporteService{

    private final PasaporteRepository pasaporteRepository;

    @Autowired
    public PasaporteServiceImpl(PasaporteRepository pasaporteRepository) {
        this.pasaporteRepository = pasaporteRepository;
    }

    public Pasaporte createPasaporte(Pasaporte pasaporte) {
        return pasaporteRepository.save(pasaporte);
    }


    public Optional<Pasaporte> getPasaporteById(Long id) {
        return pasaporteRepository.findById(id);
    }


    public List<Pasaporte> getAllPasaportes() {
        return pasaporteRepository.findAll();
    }


    public Pasaporte updatePasaporte(Long id, Pasaporte updatedPasaporte) {
        return pasaporteRepository.findById(id).map(pasaporte -> {
            pasaporte.setNumero(updatedPasaporte.getNumero());
            pasaporte.setPasajero(updatedPasaporte.getPasajero());
            return pasaporteRepository.save(pasaporte);
        }).orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));
    }


    public void deletePasaporte(Long id) {
        pasaporteRepository.deleteById(id);
    }
}
