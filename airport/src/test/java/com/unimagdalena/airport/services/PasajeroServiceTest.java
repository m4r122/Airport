package com.unimagdalena.airport.services;

import static org.junit.jupiter.api.Assertions.*;
import com.unimagdalena.airport.entities.Pasajero;
import com.unimagdalena.airport.repositories.PasajeroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PasajeroServiceTest {
    @Mock
    private PasajeroRepository pasajeroRepository;


    @InjectMocks
    private PasajeroServiceImpl pasajeroService;


    public PasajeroServiceTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void CreatePasajero() {
        Pasajero pasajero = new Pasajero();
        pasajero.setId(1L);
        pasajero.setNombre("Pasajero");
        pasajero.setNID("1234");


        when(pasajeroRepository.save(any(Pasajero.class))).thenReturn(pasajero);


        Pasajero created = pasajeroService.createPasajero(pasajero);


        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(pasajero.getId(), created.getId());
        assertEquals(pasajero.getNombre(), created.getNombre());
        assertEquals(pasajero.getNID(), created.getNID());


        verify(pasajeroRepository,times(1)).save(pasajero);
    }


    @Test
    void getPasajeroById(){
        Pasajero pasajero = new Pasajero();
        pasajero.setId(2L);
        pasajero.setNombre("Thomas");
        pasajero.setNID("5678");


        when(pasajeroRepository.findById(2L)).thenReturn(Optional.of(pasajero));


        Optional<Pasajero> found = pasajeroService.getPasajeroById(2L);


        assertTrue(found.isPresent());
        assertEquals(pasajero.getNombre(), found.get().getNombre());


        verify(pasajeroRepository,times(1)).findById(2L);
    }


    @Test
    void getAllPasajeros(){
        Pasajero pasajero = new Pasajero();
        pasajero.setId(3L);
        pasajero.setNombre("Jupiter");
        pasajero.setNID("9561");


        Pasajero pasajero2 = new Pasajero();
        pasajero2.setId(4L);
        pasajero2.setNombre("Caspián");
        pasajero2.setNID("8965");


        Pasajero pasajero3 = new Pasajero();
        pasajero3.setId(5L);
        pasajero3.setNombre("Sofía");
        pasajero3.setNID("1423");


        List<Pasajero> pasajerosNow = Arrays.asList(pasajero, pasajero2, pasajero3);


        when(pasajeroRepository.findAll()).thenReturn(pasajerosNow);


        List<Pasajero> found = pasajeroService.getAllPasajeros();


        assertNotNull(found);
        assertEquals(3, found.size());
        assertEquals("Jupiter", found.get(0).getNombre());
        assertEquals("Caspián", found.get(1).getNombre());
        assertEquals("Sofía", found.get(2).getNombre());
        verify(pasajeroRepository,times(1)).findAll();
    }


    @Test
    void updatePasajero(){
        Pasajero pasajero = new Pasajero();
        pasajero.setId(12L);
        pasajero.setNombre("Lila");
        pasajero.setNID("2648");


        Pasajero updatedPasajero = new Pasajero();
        updatedPasajero.setId(12L);
        updatedPasajero.setNombre("Lilia");
        updatedPasajero.setNID("2679");


        when(pasajeroRepository.findById(12L)).thenReturn(Optional.of(pasajero));
        when(pasajeroRepository.save(any(Pasajero.class))).thenReturn(updatedPasajero);


        Pasajero result = pasajeroService.updatePasajero(12L, updatedPasajero);


        assertEquals("Lilia", result.getNombre());
        assertEquals("2679", result.getNID());
        verify(pasajeroRepository, times(1)).save(pasajero);
    }


    @Test
    void deletePasajero(){
        pasajeroService.deletePasajero(6L);
        verify(pasajeroRepository, times(1)).deleteById(6L);
    }

}