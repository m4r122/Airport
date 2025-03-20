package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Aerolinea;
import com.unimagdalena.airport.repositories.AerolineaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AerolineaServiceTest {

    @Mock
    private AerolineaRepository aerolineaRepository;

    @InjectMocks
    private AerolineaServiceImpl aerolineaService;

    public AerolineaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createAerolinea() {
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setId(1L);
        aerolinea.setNombre("Avianca");

        when(aerolineaRepository.save(any(Aerolinea.class))).thenReturn(aerolinea);

        Aerolinea resultado = aerolineaService.createAerolinea(aerolinea);

        assertNotNull(resultado);
        assertNotNull(resultado.getId()); // Ahora no fallará porque tiene un id
        assertEquals(1L, resultado.getId());
        assertEquals("Avianca", resultado.getNombre());

        verify(aerolineaRepository, times(1)).save(any(Aerolinea.class));
    }
    @Test
    void getAreolineaById(){
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setId(1L);
        aerolinea.setNombre("Avianca");
        when(aerolineaRepository.findById(1L)).thenReturn(Optional.of(aerolinea));

        Optional<Aerolinea> foundAerolinea = aerolineaService.getAerolineaById(1L);

        assertTrue(foundAerolinea.isPresent());
        assertEquals("Avianca", foundAerolinea.get().getNombre());
        verify(aerolineaRepository, times(1)).findById(1L);
    }
    @Test
    void getAllAerolineas(){
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setId(1L);
        aerolinea.setNombre("Avianca");
        Aerolinea aerolinea1 = new Aerolinea();
        aerolinea1.setId(2L);
        aerolinea1.setNombre("Latam");
        Aerolinea aerolinea2 = new Aerolinea();
        aerolinea2.setId(3L);
        aerolinea2.setNombre("Wingo");

        List<Aerolinea> aerolineasSimuladas = Arrays.asList(aerolinea, aerolinea1, aerolinea2);

        when(aerolineaRepository.findAll()).thenReturn(aerolineasSimuladas);

        List<Aerolinea> resultado = aerolineaService.getAllAerolineas();
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Avianca", resultado.get(0).getNombre());
        assertEquals("Latam", resultado.get(1).getNombre());
        assertEquals("Wingo", resultado.get(2).getNombre());
        verify(aerolineaRepository, times(1)).findAll();
    }
    @Test
    void deleteAerolinea() {
        Long id = 1L;
        doNothing().when(aerolineaRepository).deleteById(id);
        aerolineaService.deleteAerolinea(id);
        verify(aerolineaRepository, times(1)).deleteById(id);
    }

}