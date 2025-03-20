package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Aerolinea;
import com.unimagdalena.airport.repositories.AerolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    private final AerolineaRepository aerolineaRepository;

    @Autowired
    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    public Aerolinea createAerolinea(Aerolinea aerolinea) {
        return aerolineaRepository.save(aerolinea);
    }

    public Optional<Aerolinea> getAerolineaById(long id) {
        return aerolineaRepository.findById(id);
    }

    public List<Aerolinea> getAllAerolineas() {
        return aerolineaRepository.findAll();
    }

    public void deleteAerolinea(long id) {
        aerolineaRepository.deleteById(id);
    }

}
