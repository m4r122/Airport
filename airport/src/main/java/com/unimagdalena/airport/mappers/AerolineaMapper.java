package com.unimagdalena.airport.mappers;


import com.unimagdalena.airport.dtos.AerolineaDTO;
import com.unimagdalena.airport.entities.Aerolinea;
import com.unimagdalena.airport.entities.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface AerolineaMapper {

    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);

    @Mapping(source = "vuelos", target = "vuelosIds", qualifiedByName = "mapVuelosToIds" )
    AerolineaDTO aerolineaToAerolineDTO(Aerolinea aerolinea);

    @Named("mapVuelosToIds")
    static Set<Long> mapVuelosToIds(Set<Vuelo> vuelos) {
        if (vuelos == null) {
            return Collections.emptySet();
        }
        return vuelos.stream().map(Vuelo::getId).collect(Collectors.toSet());
    }
}
