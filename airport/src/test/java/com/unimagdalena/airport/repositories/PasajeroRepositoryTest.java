package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Aerolinea;
import com.unimagdalena.airport.entities.Pasajero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasajeroRepositoryTest {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @BeforeEach
    void setUp() {
        postgreSQLContainer.start();

        Pasajero pasajero = new Pasajero();
        pasajero.setNombre("Karen");
        pasajero.setNID("NID1");
        pasajeroRepository.save(pasajero);

        Pasajero pasajero2 = new Pasajero();
        pasajero2.setNombre("Camila");
        pasajero2.setNID("NID2");
        pasajeroRepository.save(pasajero2);

        Pasajero pasajero3 = new Pasajero();
        pasajero3.setNombre("Julian");
        pasajero3.setNID("NID3");
        pasajeroRepository.save(pasajero3);

    }

    @Test
    public void findByNombre() {
        Optional<Pasajero> result = pasajeroRepository.findByNombre("Karen");

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Karen");
    }

    @Test
    public void findByNID() {
        Optional<Pasajero> result = pasajeroRepository.findByNID("NID1");
        assertThat(result).isPresent();
        assertThat(result.get().getNID()).isEqualTo("NID1");
    }

    @Test
    public void findByNombreContainingIgnoreCase() {
        List<Pasajero> resultado = pasajeroRepository.findByNombreContainingIgnoreCase("are");

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.stream().anyMatch(a->a.getNombre().equals("Karen")));
    }

    @Test
    public void findAllByOrderByNombreAsc() {
        List<Pasajero> result = pasajeroRepository.findAllByOrderByNombreAsc();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getNombre()).isEqualTo("Camila");
        assertThat(result.get(1).getNombre()).isEqualTo("Julian");
        assertThat(result.get(2).getNombre()).isEqualTo("Karen");
    }

    @Test
    public void existsByNID() {
        assertThat(pasajeroRepository.existsByNID("NID1")).isTrue();
        assertThat(pasajeroRepository.existsByNID("NID2")).isTrue();
        assertThat(pasajeroRepository.existsByNID("NID3")).isTrue();
        assertThat(pasajeroRepository.existsByNID("NID4")).isFalse();
    }

}