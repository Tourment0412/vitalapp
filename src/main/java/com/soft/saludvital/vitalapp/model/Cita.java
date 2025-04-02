package com.soft.saludvital.vitalapp.model;

import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita {
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime fechaHora;
    private Paciente paciente;
    private Medico medico;
    private String motivo;
    private String estado; // Ej: "Programada", "Cancelada", "Completada"
    private String observaciones;

    @Builder
    public Cita (LocalDateTime fechaHora, Paciente paciente, Medico medico, String motivo, String estado, String observaciones){
        //TODO hacer el this.id=codigoautoamtico
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.motivo = motivo;
        this.estado = estado;
        this.observaciones = observaciones;
    }
}
