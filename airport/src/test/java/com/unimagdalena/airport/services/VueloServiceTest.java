package com.unimagdalena.airport.services;


import com.unimagdalena.airport.entities.Vuelo;
import com.unimagdalena.airport.repositories.VueloRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VueloServiceTest {

    @Mock
    private VueloRepository vueloRepository;

    @InjectMocks
    private VueloService vueloService;

    public VueloServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVuelo() {
        Vuelo vuelo1 = new Vuelo();
        UUID numeroVuelo1 = UUID.randomUUID();
        vuelo1.setNumeroVuelo(numeroVuelo1);
        vuelo1.setId(1L);
        vuelo1.setOrigen("Bogotá");
        vuelo1.setDestino("Medellín");

        when(vueloRepository.save(any(Vuelo.class))).thenReturn(vuelo1);

        Vuelo resultado = vueloService.createVuelo(vuelo1);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals(numeroVuelo1, resultado.getNumeroVuelo());
        assertEquals(1L, resultado.getId());
        assertEquals("Bogotá", resultado.getOrigen());
        assertEquals("Medellín", resultado.getDestino());
        verify(vueloRepository, times(1)).save(any(Vuelo.class));
    }

    @Test
    void getVueloById(){
        Vuelo vuelo1 = new Vuelo();
        UUID numeroVuelo1 = UUID.randomUUID();
        vuelo1.setNumeroVuelo(numeroVuelo1);
        vuelo1.setId(1L);
        vuelo1.setOrigen("Santa Marta");
        vuelo1.setDestino("Bogotá");

        when(vueloRepository.findById(1L)).thenReturn(Optional.of(vuelo1));
        Optional<Vuelo> foundVuelo = Optional.ofNullable(vueloService.getVueloById(1L));

        assertTrue(foundVuelo.isPresent());
        assertEquals(numeroVuelo1, foundVuelo.get().getNumeroVuelo());
        assertEquals("Santa Marta", foundVuelo.get().getOrigen());
        assertEquals("Bogotá", foundVuelo.get().getDestino());
        verify(vueloRepository, times(1)).findById(1L);
    }
    @Test
    void getAllVuelos() {
        Vuelo vuelo1 = new Vuelo();
        UUID numeroVuelo1 = UUID.randomUUID();
        vuelo1.setNumeroVuelo(numeroVuelo1);
        vuelo1.setId(2L);
        vuelo1.setOrigen("Bogotá");
        vuelo1.setDestino("Medellín");

        Vuelo vuelo2 = new Vuelo();
        UUID numeroVuelo2 = UUID.randomUUID();
        vuelo2.setNumeroVuelo(numeroVuelo2);
        vuelo1.setId(3L);
        vuelo2.setOrigen("Bogotá");
        vuelo2.setDestino("Cartagena");

        Vuelo vuelo3 = new Vuelo();
        UUID numeroVuelo3 = UUID.randomUUID();
        vuelo3.setNumeroVuelo(numeroVuelo3);
        vuelo3.setId(1L);
        vuelo3.setOrigen("Santa Marta");
        vuelo3.setDestino("Bogotá");

        List<Vuelo> vuelosSimulados = Arrays.asList(vuelo1, vuelo2, vuelo3);

        when(vueloRepository.findAll()).thenReturn(vuelosSimulados);

        List<Vuelo> resultado = vueloService.getAllVuelos();
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals(numeroVuelo1, resultado.get(0).getNumeroVuelo());
        assertEquals(numeroVuelo2, resultado.get(1).getNumeroVuelo());
        assertEquals(numeroVuelo3, resultado.get(2).getNumeroVuelo());
        verify(vueloRepository, times(1)).findAll();
    }
    @Test
    void updateVuelo() {
        Vuelo existente = new Vuelo();
        UUID numeroVueloexistente = UUID.randomUUID();
        existente.setNumeroVuelo(numeroVueloexistente);
        existente.setId(1L);
        existente.setOrigen("Santa Marta");
        existente.setDestino("Bogotá");

        Vuelo vueloActualizado = new Vuelo();
        vueloActualizado.setId(1L);
        vueloActualizado.setNumeroVuelo(existente.getNumeroVuelo());
        vueloActualizado.setOrigen("Cartagena");
        vueloActualizado.setDestino("Cali");

        when(vueloRepository.save(vueloActualizado)).thenReturn(vueloActualizado);
        Vuelo resultado = vueloService.updateVuelo(vueloActualizado);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cartagena", resultado.getOrigen());
        assertEquals("Cali", resultado.getDestino());
        verify(vueloRepository, times(1)).save(vueloActualizado);
    }
    @Test
    void deleteVuelo() {
        Long id = 1L;
        doNothing().when(vueloRepository).deleteById(id);
        vueloService.deleteVuelo(id);
        verify(vueloRepository, times(1)).deleteById(id);
    }
    @Test
    void findVuelosByOrigen (){
        String origen = "Bogotá";

        Vuelo vuelo1 = new Vuelo();
        UUID numeroVuelo1 = UUID.randomUUID();
        vuelo1.setNumeroVuelo(numeroVuelo1);
        vuelo1.setId(1L);
        vuelo1.setOrigen("Bogotá");
        vuelo1.setDestino("Santa Marta");

        Vuelo vuelo2 = new Vuelo();
        UUID numeroVuelo2 = UUID.randomUUID();
        vuelo2.setNumeroVuelo(numeroVuelo2);
        vuelo1.setId(2L);
        vuelo2.setOrigen("Bogotá");
        vuelo2.setDestino("Cartagena");

        List<Vuelo> vuelosEsperados = Arrays.asList(vuelo1, vuelo2);

        when(vueloRepository.findByOrigen(origen)).thenReturn(vuelosEsperados);

        List<Vuelo> resultado = vueloService.findVuelosByOrigen(origen);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Bogotá", resultado.get(0).getOrigen());
        assertEquals("Bogotá", resultado.get(1).getOrigen());
        verify(vueloRepository, times(1)).findByOrigen(origen);

    }

}