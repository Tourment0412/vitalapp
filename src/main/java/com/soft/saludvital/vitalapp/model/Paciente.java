package com.soft.saludvital.vitalapp.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente {
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;
    private HistorialMedico historialMedico;

    @Builder
    public Paciente(String nombre, String apellido, LocalDate fechaNacimiento, String genero, String direccion, String telefono, String email ){
        //TODO Hacer this.id= codigoaleatorio
        this.nombre = nombre;
        this.apellido = apellido;
        //TODO Considerar hacer un formateador para la fecha
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.historialMedico = new HistorialMedico();
    }
}
