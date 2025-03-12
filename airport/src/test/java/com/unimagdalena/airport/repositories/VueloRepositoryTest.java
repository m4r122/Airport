package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Vuelo;
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
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VueloRepositoryTest {
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");
    @Autowired
    private VueloRepository vueloRepository;

    @BeforeEach
    void setUp() {
        postgreSQLContainer.start();

        Vuelo vuelo1 = new Vuelo();
        vuelo1.setNumeroVuelo(UUID.randomUUID());
        vuelo1.setOrigen("Bogotá");
        vuelo1.setDestino("Medellín");

        Vuelo vuelo2 = new Vuelo();
        vuelo2.setNumeroVuelo(UUID.randomUUID());
        vuelo2.setOrigen("Bogotá");
        vuelo2.setDestino("Cartagena");

        vueloRepository.saveAll(List.of(vuelo1, vuelo2));
        vueloRepository.flush();
    }
    @Test
    public void findByOrigen(){
        List<Vuelo> vuelos = vueloRepository.findByOrigen("Bogotá");

        assertThat(vuelos).isNotEmpty();
        assertThat(vuelos).hasSize(2);
        assertThat(vuelos.get(0).getOrigen()).isEqualTo("Bogotá");
        assertThat(vuelos.get(1).getOrigen()).isEqualTo("Bogotá");

    }
}