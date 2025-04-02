package com.soft.saludvital.vitalapp.model;


import lombok.*;

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
}
