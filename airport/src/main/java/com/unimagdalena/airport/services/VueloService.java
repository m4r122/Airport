package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Vuelo;

import java.util.List;

public interface VueloService {
    Vuelo createVuelo (Vuelo vuelo);
    Vuelo getVueloById(long id);
    List<Vuelo> getAllVuelos();
    Vuelo updateVuelo(Vuelo vuelo);
    void deleteVuelo(long id);
    List<Vuelo> findVuelosByOrigen(String origen);
}
