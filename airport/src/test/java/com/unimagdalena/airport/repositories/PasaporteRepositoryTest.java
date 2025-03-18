package com.unimagdalena.airport.repositories;

import static org.junit.jupiter.api.Assertions.*;
import com.unimagdalena.airport.entities.Pasaporte;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasaporteRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @Autowired
    private PasaporteRepository pasaporteRepository;

    @BeforeEach
    void setUp() {
        postgreSQLContainer.start();
        Pasaporte pasaporte = new Pasaporte();
        pasaporte.setNumero("12345");
        pasaporteRepository.save(pasaporte);


    }
    @Test
    public void findByNumero() {
        Optional<Pasaporte> pasaporte = pasaporteRepository.findByNumero("12345");
        assertThat(pasaporte).isPresent();
        assertThat(pasaporte.get().getNumero()).isEqualTo("12345");
    }
    @Test
    public void existsByNumero() {
        assertThat(pasaporteRepository.existsByNumero("12345")).isTrue();
        assertThat(pasaporteRepository.existsByNumero("67891")).isFalse();
    }

}