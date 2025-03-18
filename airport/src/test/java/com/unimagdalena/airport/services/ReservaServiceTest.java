package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Pasajero;
import com.unimagdalena.airport.entities.Reserva;
import com.unimagdalena.airport.entities.Vuelo;
import com.unimagdalena.airport.repositories.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    public ReservaServiceTest (){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReserva() {
        Pasajero pasajero = new Pasajero();
        pasajero.setId(1L);
        pasajero.setNombre("Karen Mora");

        Vuelo vuelo = new Vuelo();
        vuelo.setId(10L);
        vuelo.setNumeroVuelo(UUID.randomUUID());
        vuelo.setOrigen("Bogotá");
        vuelo.setDestino("Santa marta");

        Reserva reserva = new Reserva();
        UUID codigoReserva = UUID.randomUUID();
        reserva.setCodigoReserva(codigoReserva);
        reserva.setId(100L);
        reserva.setPasajero(pasajero);
        reserva.setVuelo(vuelo);

        when(reservaRepository.save(ArgumentMatchers.any(Reserva.class))).thenReturn(reserva);

        Reserva resultado = reservaService.createReserva(reserva);

        assertNotNull(resultado);
        assertNotNull(resultado.getPasajero());
        assertEquals("Karen Mora", resultado.getPasajero().getNombre());
        assertEquals("Bogotá", resultado.getVuelo().getOrigen());
        verify(reservaRepository, times(1)).save(ArgumentMatchers.any(Reserva.class));
    }
    @Test
    void getReservaByCodigo(){
        Pasajero pasajero = new Pasajero();
        pasajero.setId(2L);
        pasajero.setNombre("Camila Gomez");

        Vuelo vuelo = new Vuelo();
        vuelo.setId(11L);
        vuelo.setNumeroVuelo(UUID.randomUUID());
        vuelo.setOrigen("Bogotá");
        vuelo.setDestino("Cali");

        Reserva reserva = new Reserva();
        UUID codigoReserva = UUID.randomUUID();
        reserva.setCodigoReserva(codigoReserva);
        reserva.setId(101L);
        reserva.setPasajero(pasajero);
        reserva.setVuelo(vuelo);

        when(reservaRepository.findByCodigoReserva(codigoReserva)).thenReturn(Optional.of(reserva));
        Optional<Reserva> foundReserva = Optional.ofNullable(reservaService.getReservaByCodigo(codigoReserva));

        assertTrue(foundReserva.isPresent());
        assertEquals(101L, foundReserva.get().getId());
        assertEquals(pasajero, foundReserva.get().getPasajero());
        assertEquals(vuelo, foundReserva.get().getVuelo());
        verify(reservaRepository, times(1)).findByCodigoReserva(codigoReserva);
    }

    @Test
    void getAllReservas(){
        Pasajero pasajero = new Pasajero();
        pasajero.setId(2L);
        pasajero.setNombre("Camila Gomez");

        Vuelo vuelo = new Vuelo();
        vuelo.setId(11L);
        vuelo.setNumeroVuelo(UUID.randomUUID());
        vuelo.setOrigen("Bogotá");
        vuelo.setDestino("Cali");

        Reserva reserva = new Reserva();
        UUID codigoReserva = UUID.randomUUID();
        reserva.setCodigoReserva(codigoReserva);
        reserva.setId(101L);
        reserva.setPasajero(pasajero);
        reserva.setVuelo(vuelo);

        Pasajero pasajero1 = new Pasajero();
        pasajero1.setId(1L);
        pasajero1.setNombre("Karen Mora");

        Vuelo vuelo1 = new Vuelo();
        vuelo1.setId(10L);
        vuelo1.setNumeroVuelo(UUID.randomUUID());
        vuelo1.setOrigen("Bogotá");
        vuelo1.setDestino("Santa marta");

        Reserva reserva1 = new Reserva();
        UUID codigoReserva1 = UUID.randomUUID();
        reserva1.setCodigoReserva(codigoReserva1);
        reserva1.setId(100L);
        reserva1.setPasajero(pasajero1);
        reserva1.setVuelo(vuelo1);

        List<Reserva> reservasSimuladas = Arrays.asList(reserva, reserva1);

        when(reservaRepository.findAll()).thenReturn(reservasSimuladas);

        List<Reserva> resultado = reservaService.getAllReservas();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(codigoReserva, resultado.get(0).getCodigoReserva());
        assertEquals(codigoReserva1, resultado.get(1).getCodigoReserva());
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    void updateReserva() {
        Pasajero pasajero = new Pasajero();
        pasajero.setId(3L);
        pasajero.setNombre("Monica Sanchez");

        Vuelo vuelo = new Vuelo();
        vuelo.setId(12L);
        vuelo.setNumeroVuelo(UUID.randomUUID());
        vuelo.setOrigen("Bogotá");
        vuelo.setDestino("Corea del sur");

        Reserva reservaExistente = new Reserva();
        UUID codigoReserva = UUID.randomUUID();
        reservaExistente.setCodigoReserva(codigoReserva);
        reservaExistente.setId(102L);
        reservaExistente.setPasajero(pasajero);
        reservaExistente.setVuelo(vuelo);

        Vuelo vueloNuevo = new Vuelo();
        vueloNuevo.setId(12L);
        vueloNuevo.setNumeroVuelo(vuelo.getNumeroVuelo());
        vueloNuevo.setOrigen("Santa marta");
        vueloNuevo.setDestino("Corea del sur");

        Reserva reservaNueva = new Reserva();
        UUID codigoReservaNuevo = UUID.randomUUID();
        reservaNueva.setCodigoReserva(codigoReservaNuevo);
        reservaNueva.setId(102L);
        reservaNueva.setPasajero(pasajero);
        reservaNueva.setVuelo(vueloNuevo);

        when(reservaRepository.save(reservaNueva)).thenReturn(reservaNueva);

        Reserva resultado = reservaService.updateReserva(reservaNueva);
        assertNotNull(resultado);
        assertEquals(102L, resultado.getId());
        assertEquals(codigoReservaNuevo, resultado.getCodigoReserva());
        assertEquals(pasajero, resultado.getPasajero());
        assertEquals(vueloNuevo, resultado.getVuelo());
        verify(reservaRepository, times(1)).save(reservaNueva);
    }
    @Test
    void getReservaByPasajeroId(){
        Pasajero pasajero = new Pasajero();
        pasajero.setId(2L);
        pasajero.setNombre("Camila Gomez");

        Vuelo vuelo = new Vuelo();
        vuelo.setId(11L);
        vuelo.setNumeroVuelo(UUID.randomUUID());
        vuelo.setOrigen("Bogotá");
        vuelo.setDestino("Cali");

        Reserva reserva = new Reserva();
        UUID codigoReserva = UUID.randomUUID();
        reserva.setCodigoReserva(codigoReserva);
        reserva.setId(101L);
        reserva.setPasajero(pasajero);
        reserva.setVuelo(vuelo);

        Pasajero pasajero1 = new Pasajero();
        pasajero1.setId(1L);
        pasajero1.setNombre("Karen Mora");

        Vuelo vuelo1 = new Vuelo();
        vuelo1.setId(10L);
        vuelo1.setNumeroVuelo(UUID.randomUUID());
        vuelo1.setOrigen("Bogotá");
        vuelo1.setDestino("Santa marta");

        Reserva reserva1 = new Reserva();
        UUID codigoReserva1 = UUID.randomUUID();
        reserva1.setCodigoReserva(codigoReserva1);
        reserva1.setId(100L);
        reserva1.setPasajero(pasajero1);
        reserva1.setVuelo(vuelo1);

        Pasajero pasajero2 = new Pasajero();
        pasajero2.setId(3L);
        pasajero2.setNombre("Monica Sanchez");

        Vuelo vuelo2 = new Vuelo();
        vuelo2.setId(12L);
        vuelo2.setNumeroVuelo(UUID.randomUUID());
        vuelo2.setOrigen("Bogotá");
        vuelo2.setDestino("Corea del sur");

        Reserva reserva2 = new Reserva();
        UUID codigoReserva2 = UUID.randomUUID();
        reserva2.setCodigoReserva(codigoReserva2);
        reserva2.setId(102L);
        reserva2.setPasajero(pasajero2);
        reserva2.setVuelo(vuelo2);

        List<Reserva> reservas = Arrays.asList(reserva, reserva1, reserva2);

        when(reservaRepository.findByPasajeroId(pasajero2.getId())).thenReturn(reservas);

        List<Reserva> resultado = reservaService.getReservaByPasajeroId(pasajero2.getId());

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals(101L, resultado.get(0).getId());
        assertEquals(100L, resultado.get(1).getId());
        assertEquals(102L, resultado.get(2).getId());

        verify(reservaRepository, times(1)).findByPasajeroId(pasajero2.getId());

    }
}