package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Aerolinea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AerolineaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Test
    public void whenFindByNombre_thenReturnAerolinea() {
        //given
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre("Avianca");
        entityManager.persist(aerolinea);
        entityManager.flush();
        //when
        Aerolinea found = aerolineaRepository.findByNombre(aerolinea.getNombre());
        //then
        assertThat(found.getNombre()).isEqualTo(aerolinea.getNombre());
    }
}