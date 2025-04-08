package com.unimagdalena.airport.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasaporteDTO {
    private long id;
    private String numero;
    private long pasajeroId;
}
