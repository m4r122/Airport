package com.unimagdalena.airport.mappers;

import com.unimagdalena.airport.dtos.ReservaDTO;
import com.unimagdalena.airport.entities.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservaMapper {
    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);
    @Mapping(source = "vuelo.id", target = "vueloId")
    @Mapping(source = "pasajero.id", target = "pasajeroId")
    ReservaDTO reservaToReservaDTO(Reserva reserva);
}
