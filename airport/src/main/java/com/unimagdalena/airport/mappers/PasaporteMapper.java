package com.unimagdalena.airport.mappers;

import com.unimagdalena.airport.dtos.PasaporteDTO;
import com.unimagdalena.airport.entities.Pasaporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PasaporteMapper {
    PasaporteMapper INSTANCE = Mappers.getMapper(PasaporteMapper.class);

    @Mapping(source = "pasajero.id", target = "pasajeroId")
    PasaporteDTO pasaporteToPasaporteDTO(Pasaporte pasaporte);
}
