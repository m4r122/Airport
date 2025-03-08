package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    Optional<Pasajero> findByNombre(String nombre);

    Optional<Pasajero> findByNID(String NID);

    List<Pasajero> findByNombreContainingIgnoreCase(String keyword);

    @Query("SELECT COUNT(p) FROM Pasajero p WHERE p.pasaporte IS NOT NULL")
    long countPasajerosWithPasaporte();

    @Query("SELECT p FROM Pasajero p WHERE SIZE(p.reservas) > 0")
    List<Pasajero> findPasajerosWithReservas();

    @Query("SELECT p FROM Pasajero p WHERE SIZE(p.reservas) = 0")
    List<Pasajero> findPasajerosWithoutReservas();

    List<Pasajero> findAllByOrderByNombreAsc();

    boolean existsByNID(String NID);

    @Query("SELECT p FROM Pasajero p WHERE SIZE(p.reservas) >= :minReservas")
    List<Pasajero> findByMinimumReservas(int minReservas);

    @Query("SELECT p FROM Pasajero p WHERE p.pasaporte.id = :pasaporteId")
    Optional<Pasajero> findByPasaporteId(Long pasaporteId);
}

