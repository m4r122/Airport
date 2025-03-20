package com.unimagdalena.airport.services;

import com.unimagdalena.airport.entities.Reserva;
import com.unimagdalena.airport.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    public Reserva getReservaByCodigo(UUID codigo) {
        return reservaRepository.findByCodigoReserva(codigo).orElse(null);
    }
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }
    public Reserva updateReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    public List<Reserva> getReservaByPasajeroId(Long pasajeroId){
        return reservaRepository.findByPasajeroId(pasajeroId);
    }
}
