package com.unimagdalena.airport.repositories;


import com.unimagdalena.airport.entities.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {
    Optional<Pasaporte> findByNumero(String numero);

    boolean existsByNumero(String numero);

    @Query("SELECT p FROM Pasaporte p WHERE LENGTH(p.numero) = :length")
    List<Pasaporte> findByNumeroLength(int length);

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NULL")
    List<Pasaporte> findPasaportesSinPasajero();

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    List<Pasaporte> findPasaportesConPasajero();

    @Query("SELECT COUNT(p) FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    long countPasaportesConPasajero();

    @Query("SELECT p FROM Pasaporte p ORDER BY p.numero ASC")
    List<Pasaporte> findAllOrderByNumeroAsc();

    @Query("SELECT p FROM Pasaporte p JOIN p.pasajero pas WHERE LOWER(pas.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Pasaporte> findByNombrePasajeroContaining(String nombre);

    @Query("SELECT p FROM Pasaporte p WHERE SUBSTRING(p.numero, 1, 2) = :prefijo")
    List<Pasaporte> findByNumeroStartingWith(String prefijo);

    @Query("SELECT p FROM Pasaporte p WHERE p.numero LIKE :pattern")
    List<Pasaporte> findByNumeroLike(String pattern);
}
