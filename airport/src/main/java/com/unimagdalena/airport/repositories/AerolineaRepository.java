package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    Optional<Aerolinea> findByNombre(String nombre);

    List<Aerolinea> findByNombreContainingIgnoreCase(String keyword);

    @Query("SELECT a FROM Aerolinea a WHERE SIZE(a.vuelos) > 0")
    List<Aerolinea> findAerolineasWithFlights();

    @Query("SELECT COUNT(a) FROM Aerolinea a WHERE SIZE(a.vuelos) > 0")
    long countAerolineasWithFlights();

    @Query("SELECT a FROM Aerolinea a WHERE SIZE(a.vuelos) = 0")
    List<Aerolinea> findAerolineasWithoutFlights();

    List<Aerolinea> findAllByOrderByNombreAsc();

    List<Aerolinea> findByNombreStartingWithIgnoreCase(String prefix);

    @Query("SELECT a FROM Aerolinea a JOIN a.vuelos v WHERE v.id = :vueloId")
    List<Aerolinea> findByVueloId(Long vueloId);

    @Query("SELECT a FROM Aerolinea a WHERE SIZE(a.vuelos) >= :minVuelos")
    List<Aerolinea> findByMinimumFlights(int minVuelos);

    boolean existsByNombre(String nombre);

}
