package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Aerolinea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
public class AerolineaRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("airport")
                    .withUsername("postgres")
                    .withPassword("traumsirene22");

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setup() {
        aerolineaRepository.deleteAll();

        Aerolinea aerolinea1 = new Aerolinea();
        aerolinea1.setNombre("Avianca");
        aerolineaRepository.save(aerolinea1);

        Aerolinea aerolinea2 = new Aerolinea();
        aerolinea2.setNombre("Latam");
        aerolineaRepository.save(aerolinea2);
    }

    @Test
    public void findByNombre() {
        Aerolinea aerolinea = aerolineaRepository.findByNombre("Avianca");

        assertThat(aerolinea).isNotNull();
        assertThat(aerolinea.getNombre()).isEqualTo("Avianca");
    }
}
