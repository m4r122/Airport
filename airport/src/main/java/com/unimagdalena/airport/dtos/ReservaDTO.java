package com.unimagdalena.airport.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private long id;
    private UUID codigoReserva;
    private long vueloId;
    private long pasajeroId;
}
