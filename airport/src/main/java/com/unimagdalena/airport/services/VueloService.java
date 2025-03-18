package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Vuelo;
import com.unimagdalena.airport.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VueloService {

    private final VueloRepository vueloRepository;

    @Autowired
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    public Vuelo createVuelo (Vuelo vuelo){
        return vueloRepository.save(vuelo);
    }
    public Vuelo getVueloById(long id){
        return vueloRepository.findById(id).orElse(null);
    }
    public List<Vuelo> getAllVuelos(){
        return vueloRepository.findAll();
    }
    public Vuelo updateVuelo(Vuelo vuelo){
        return vueloRepository.save(vuelo);
    }
    public void deleteVuelo(long id){
        vueloRepository.deleteById(id);
    }
    public List<Vuelo> findVuelosByOrigen(String origen) {
        return vueloRepository.findByOrigen(origen);
    }

}
