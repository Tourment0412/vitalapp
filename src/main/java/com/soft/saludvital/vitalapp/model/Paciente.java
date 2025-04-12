package com.soft.saludvital.vitalapp.model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        this.historialMedico = new HistorialMedico(this, new ArrayList<>());
    }

    /**
     * Metodo que envia alertas de salud al paciente con base en los resultados del diagnostico de la cita
     */
    public void enviarAlerta() {
        System.out.println(generarAlerta());
    }

    /**
     * Metodo que genera mensajes acerca de la salud al paciente con base en los resultados del diagnostico de la cita
     * @return mensaje generado para enviarselo al paciente
     */
    public String generarAlerta() {
        List<Cita> citas = historialMedico.getListaCitas();

        if (citas == null || citas.isEmpty()) {
            return "No hay citas registradas para enviar una alerta.";
        }

        Cita ultimaCita = citas.get(citas.size() - 1);
        String gravedad = ultimaCita.getGravedadDiagnostigo();
        String diagnostico = ultimaCita.getDiagnostico();

        if (gravedad == null) {
            return "No se pudo determinar la gravedad del diagnóstico.";
        }

        String color;
        switch (gravedad.toLowerCase()) {
            case "leve":
                color = "\u001B[32m"; // Verde
                break;
            case "grave":
                color = "\u001B[33m"; // Amarillo
                break;
            case "urgente":
                color = "\u001B[31m"; // Rojo
                break;
            default:
                color = "\u001B[37m"; // Blanco
                break;
        }

        String reset = "\u001B[0m";

        return color + "\u26A0\uFE0F ALERTA DE SALUD \u26A0\uFE0F\n" +
                "Gravedad: " + gravedad.toUpperCase() + "\n" +
                "Diagnóstico: " + diagnostico + reset;
    }


}
