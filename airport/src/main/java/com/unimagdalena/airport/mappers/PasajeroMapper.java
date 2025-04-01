package com.unimagdalena.airport.mappers;

import com.unimagdalena.airport.dtos.PasajeroDTO;
import com.unimagdalena.airport.entities.Pasajero;
import com.unimagdalena.airport.entities.Reserva;
import com.unimagdalena.airport.entities.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface PasajeroMapper {
    PasajeroMapper INSTANCE = Mappers.getMapper(PasajeroMapper.class);

    @Mapping(source = "pasaporte.id", target = "pasaporteId")
    @Mapping(source = "reservas", target = "reservasIds", qualifiedByName = "mapReservasToIds")
    PasajeroDTO PasajeroToPasajeroDTO(Pasajero pasajero);

    @Named("mapReservasToIds")
    static List<Long> mapReservasToIds(Set<Reserva> reservas) {
        if (reservas == null) {
            return Collections.emptyList();
        }
        return reservas.stream().map(Reserva::getId).collect(Collectors.toList());
    }
}
