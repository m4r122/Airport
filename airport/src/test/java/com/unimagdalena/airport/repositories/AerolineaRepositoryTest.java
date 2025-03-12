package com.unimagdalena.airport.repositories;

import com.unimagdalena.airport.entities.Aerolinea;
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
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AerolineaRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @Autowired
    private AerolineaRepository aerolineaRepository;
    @Autowired
    private VueloRepository vueloRepository;

    @BeforeEach
    public void setup() {
        postgreSQLContainer.start();
        Vuelo vuelo1 = new Vuelo();
        vuelo1.setNumeroVuelo(UUID.randomUUID());
        vuelo1.setOrigen("Bogotá");
        vuelo1.setDestino("Medellin");
        vuelo1 = vueloRepository.save(vuelo1);


        Vuelo vuelo2 = new Vuelo();
        vuelo2.setNumeroVuelo(UUID.randomUUID());
        vuelo2.setOrigen("Santa Marta");
        vuelo2.setDestino("Cali");
        vuelo2 = vueloRepository.save(vuelo2);

        Vuelo vuelo3 = new Vuelo();
        vuelo3.setNumeroVuelo(UUID.randomUUID());
        vuelo3.setOrigen("Bucaramanga");
        vuelo3.setDestino("Santa Marta");
        vuelo3 = vueloRepository.save(vuelo3);

        Vuelo vuelo4 = new Vuelo();
        vuelo4.setNumeroVuelo(UUID.randomUUID());
        vuelo4.setOrigen("Cartagena");
        vuelo4.setDestino("Barranquilla");
        vuelo4 = vueloRepository.save(vuelo4);

        Vuelo vuelo5 = new Vuelo();
        vuelo5.setNumeroVuelo(UUID.randomUUID());
        vuelo5.setOrigen("Zapatoca");
        vuelo5.setDestino("Palmira");
        vuelo5 = vueloRepository.save(vuelo5);


        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre("Avianca");
        aerolinea.setVuelos(Set.of(vuelo1, vuelo2));
        aerolineaRepository.save(aerolinea);


        Aerolinea aerolinea2 = new Aerolinea();
        aerolinea2.setNombre("LATAM");
        aerolinea2.setVuelos(Set.of(vuelo3));
        aerolineaRepository.save(aerolinea2);


        Aerolinea aerolinea3 = new Aerolinea();
        aerolinea3.setNombre("Wingo");
        aerolinea3.setVuelos(Set.of(vuelo4));
        aerolineaRepository.save(aerolinea3);

    }

    @Test
    public void whenFindByNombre_thenReturnAerolinea() {
        Aerolinea found = aerolineaRepository.findByNombre("Avianca");
        assertThat(found).isNotNull();
        assertThat(found.getNombre()).isEqualTo("Avianca");
    }
    @Test
    public void testfindByNombreContainingIgnoreCase(){

        List<Aerolinea> resultado = aerolineaRepository.findByNombreContainingIgnoreCase("via");

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.stream().anyMatch(a->a.getNombre().equals("Avianca")));
    }


    @Test
    public void testFindAerolineasWithFlights(){

        List<Aerolinea> aerolineasConVuelos = aerolineaRepository.findAerolineasWithFlights();

        assertThat(aerolineasConVuelos).isNotEmpty();
        assertThat(aerolineasConVuelos.get(0).getNombre()).isEqualTo("Avianca");

    }

    @Test
    void testFindAllByOrderByNombreAsc() {
        List<Aerolinea> result = aerolineaRepository.findAllByOrderByNombreAsc();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getNombre()).isEqualTo("Avianca");
        assertThat(result.get(1).getNombre()).isEqualTo("LATAM");
        assertThat(result.get(2).getNombre()).isEqualTo("Wingo");
    }
    @Test
    void testFindByNombreStartingWithIgnoreCase() {


        List<Aerolinea> result = aerolineaRepository.findByNombreStartingWithIgnoreCase("Avi");

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Avianca");
    }
    @Test
    void testExistsByNombre() {
        assertThat(aerolineaRepository.existsByNombre("Avianca")).isTrue();
        assertThat(aerolineaRepository.existsByNombre("LATAM")).isTrue();
        assertThat(aerolineaRepository.existsByNombre("Wingo")).isTrue();

        assertThat(aerolineaRepository.existsByNombre("American Airlines")).isFalse();
        assertThat(aerolineaRepository.existsByNombre("Delta")).isFalse();
        assertThat(aerolineaRepository.existsByNombre("Spirit")).isFalse();
    }



}
