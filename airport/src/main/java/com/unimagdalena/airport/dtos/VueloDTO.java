package com.unimagdalena.airport.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapping;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VueloDTO {
    private long id;
    private String origen;
    private String destino;
    private Set<Long>AerolineaIds;
}
