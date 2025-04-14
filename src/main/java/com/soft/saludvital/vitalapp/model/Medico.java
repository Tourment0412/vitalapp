package com.soft.saludvital.vitalapp.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

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
        this.id = UUID.randomUUID().toString();
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
        Cita cita = Cita.builder()
                .fechaHora(fecha)
                .paciente(paciente)
                .medico(this)
                .motivo(motivo)
                .estado(estado)
                .observaciones(observaciones)
                .build();

        if (paciente.getHistorialMedico() != null) {
            paciente.getHistorialMedico().getListaCitas().add(cita);
        } else {
            System.out.println("El paciente no tiene historial médico registrado.");
        }

        return cita;
    }

    /**
     * Actualiza los datos del medico
     * @param nombre Nuevo nombre del medico
     * @param apellido Nuevo apellido del medico
     * @param especialidad Nueva especialidad del medico
     * @param telefono Nuevo telefono del medico
     * @param email Nuevo email del medico
     */
    public void actualizarMedico(String nombre,String apellido,String especialidad,String telefono,String email) {
        setNombre(nombre);
        setApellido(apellido);
        setEspecialidad(especialidad);
        setTelefono(telefono);
        setEmail(email);
    }

}
