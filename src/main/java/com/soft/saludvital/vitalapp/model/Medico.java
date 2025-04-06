package com.soft.saludvital.vitalapp.model;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico {
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
    //TODO considerar implementar una lista de las citas medicas que tiene

    @Builder
    public Medico( String nombre, String apellido, String especialidad, String telefono, String email) {
        //TODO hacer this.id=codigoautomatico
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email=email;
    }

    /**
     * Matodo que permite crear una cita agendada
     * @param fecha Fecha y hora donde se realizara la cita
     * @param paciente Paciente que se le agendara la cita
     * @param motivo Motivo del agendamiento de la cita
     * @param estado Estado de la cita
     * @param observaciones Observaciones del medico sobre la cita
     * @return
     */
    public Cita agendarCita(LocalDateTime fecha,Paciente paciente, String motivo, String estado, String observaciones) {
        return new Cita(fecha,
                paciente,
                this,
                motivo,
                estado,
                observaciones);
    }
}
