package com.unimagdalena.airport.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasajeroDTO {
    private long id;
    private String nombre;
    private long pasaporteId;
    private List<Long> reservasIds;
}
