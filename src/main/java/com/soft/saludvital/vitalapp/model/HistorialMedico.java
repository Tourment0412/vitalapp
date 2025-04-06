package com.soft.saludvital.vitalapp.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistorialMedico {
    @EqualsAndHashCode.Include
    private String id;
    private Paciente paciente;
    private List<Cita> listaCitas;

    @Builder
    public HistorialMedico(Paciente paciente,  List<Cita> listaCitas) {
        this.paciente = paciente;
        this.listaCitas = listaCitas;
    }
}
