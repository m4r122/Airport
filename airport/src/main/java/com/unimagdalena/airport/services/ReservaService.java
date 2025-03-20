package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Reserva;

import java.util.List;
import java.util.UUID;

public interface ReservaService {
    Reserva createReserva(Reserva reserva);
    Reserva getReservaByCodigo(UUID codigo);
    List<Reserva> getAllReservas();
    Reserva updateReserva(Reserva reserva);
    List<Reserva> getReservaByPasajeroId(Long pasajeroId);
}
