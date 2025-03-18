package com.unimagdalena.airport.repositories;

import static org.junit.jupiter.api.Assertions.*;
import com.unimagdalena.airport.entities.Pasajero;
import com.unimagdalena.airport.entities.Reserva;
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReservaRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @BeforeEach
    void setUp() {
        postgreSQLContainer.start();
    }
    @Test
    public void findByCodigoReserva(){
        UUID codigoReserva = UUID.randomUUID();
        Reserva reserva = new Reserva();
        reserva.setCodigoReserva(codigoReserva);

        reservaRepository.save(reserva);
        reservaRepository.flush();

        // Buscar la reserva por código
        Optional<Reserva> result = reservaRepository.findByCodigoReserva(codigoReserva);

        // Verificaciones
        assertThat(result).isPresent();
        assertThat(result.get().getCodigoReserva()).isEqualTo(codigoReserva);
    }
    @Test
    public void existsByCodigoReserva(){
        Reserva reserva1 = new Reserva();
        UUID codigoReserva1 = UUID.randomUUID();
        reserva1.setCodigoReserva(codigoReserva1);
        reservaRepository.save(reserva1);

        Reserva reserva2 = new Reserva();
        UUID codigoReserva2 = UUID.randomUUID();
        reserva2.setCodigoReserva(codigoReserva2);
        reservaRepository.save(reserva2);

        Reserva reserva3 = new Reserva();
        UUID codigoReserva3 = UUID.randomUUID();
        reserva3.setCodigoReserva(codigoReserva3);
        reservaRepository.save(reserva3);

        assertThat(reservaRepository.existsByCodigoReserva(codigoReserva1)).isTrue();
        assertThat(reservaRepository.existsByCodigoReserva(codigoReserva2)).isTrue();
        assertThat(reservaRepository.existsByCodigoReserva(codigoReserva3)).isTrue();
    }
}