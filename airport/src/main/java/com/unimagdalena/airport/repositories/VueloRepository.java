package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

    @Query("SELECT v FROM Vuelo v WHERE LOWER(v.origen) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(v.destino) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Vuelo> searchByOrigenOrDestino(String texto);

    List<Vuelo> findByReservasIsEmpty();

    List<Vuelo> findByReservasIsNotEmpty();

    @Query("SELECT v FROM Vuelo v ORDER BY SIZE(v.reservas) DESC")
    List<Vuelo> findAllOrderByReservasDesc();

    @Query("SELECT v FROM Vuelo v WHERE v.numeroVuelo = :numeroVuelo")
    Vuelo findByNumeroVuelo(UUID numeroVuelo);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen AND SIZE(v.reservas) > 0")
    List<Vuelo> findReservadosDesde(String origen);

    List<Vuelo> findByOrigen(String origen);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen AND v.destino = :destino AND SIZE(v.reservas) = 0")
    List<Vuelo> findDisponiblesPorRuta(String origen, String destino);

    @Query("SELECT v FROM Vuelo v JOIN FETCH v.aerolineas a WHERE a.nombre = :nombreAerolinea")
    List<Vuelo> findByNombreAerolinea(String nombreAerolinea);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen OR v.destino = :destino")
    List<Vuelo> findByOrigenOrDestinoExacto(String origen, String destino);
}
