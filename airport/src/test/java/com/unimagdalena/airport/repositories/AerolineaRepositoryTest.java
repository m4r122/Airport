package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Aerolinea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AerolineaRepositoryTest {
    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Test
    public void testGuardarYBuscarUnaAerolinea() {
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre("Avianca");

        Aerolinea saved = aerolineaRepository.save(aerolinea);

        Optional<Aerolinea> found = aerolineaRepository.findByNombre(saved.getNombre());
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Avianca");

    }

}