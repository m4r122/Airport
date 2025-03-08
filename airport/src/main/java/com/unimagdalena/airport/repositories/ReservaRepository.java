package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findByCodigoReserva(UUID codigoReserva);

    boolean existsByCodigoReserva(UUID codigoReserva);

    List<Reserva> findByPasajeroId(Long pasajeroId);

    List<Reserva> findByVueloId(Long vueloId);

    @Query("SELECT r FROM Reserva r JOIN r.vuelo v WHERE v.origen = :origen")
    List<Reserva> findByOrigenVuelo(String origen);

    @Query("SELECT r FROM Reserva r JOIN r.vuelo v WHERE v.destino = :destino")
    List<Reserva> findByDestinoVuelo(String destino);

    @Query("SELECT r FROM Reserva r JOIN r.pasajero p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Reserva> findByNombrePasajeroContaining(String nombre);

    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.vuelo.id = :vueloId")
    long countReservasByVueloId(Long vueloId);

    @Query("SELECT r FROM Reserva r ORDER BY r.codigoReserva ASC")
    List<Reserva> findAllOrderByCodigoReservaAsc();

    @Query("SELECT r FROM Reserva r JOIN FETCH r.vuelo v JOIN FETCH r.pasajero p WHERE v.id = :vueloId AND p.id = :pasajeroId")
    Optional<Reserva> findByVueloIdAndPasajeroId(Long vueloId, Long pasajeroId);
}
