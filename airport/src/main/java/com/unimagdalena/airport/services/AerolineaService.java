package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {
    Aerolinea createAerolinea(Aerolinea aerolinea);
    Optional<Aerolinea> getAerolineaById(long id);
    List<Aerolinea> getAllAerolineas();
    void deleteAerolinea(long id);
}
