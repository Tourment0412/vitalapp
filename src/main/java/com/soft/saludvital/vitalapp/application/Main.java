package com.soft.saludvital.vitalapp.application;


import com.soft.saludvital.vitalapp.model.Cita;
import com.soft.saludvital.vitalapp.model.Medico;
import com.soft.saludvital.vitalapp.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Medico medico = quemarMedico();
        Paciente paciente = quemarPaciente();
        LocalDateTime fechaHora = LocalDateTime.of(2025, 4, 10, 15, 30);
        Cita cita = medico.agendarCita(
                fechaHora,
                paciente,
                "Chequeo cardiologico de rutina",
                "Programada",
                "Paciente indica fatiga al caminar"
        );

        // Alerta LEVE
        cita.definirDiagnostico("Leve", "El paciente presenta síntomas menores de resfriado común, se recomienda reposo y monitoreo.");
        String alerta = paciente.generarAlerta();
        paciente.enviarAlerta();
        System.out.println("\n");
        // Alerta GRAVE
        cita.definirDiagnostico("Grave", "El paciente sufre problemas severos del corazón, es necesario agendar una cita urgente con especialista.");
        String alerta2 = paciente.generarAlerta();
        paciente.enviarAlerta();
        System.out.println("\n");
        // Alerta URGENTE
        cita.definirDiagnostico("Urgente", "El paciente muestra signos de un posible infarto. Se requiere atención médica inmediata.");
        String alerta3 = paciente.generarAlerta();
        paciente.enviarAlerta();
        System.out.println("\n");


    }

    /**
     * Metodo que quema los datos de un medico
     * @return Medico con datos quemados
     */
    public static Medico quemarMedico() {
        return Medico.builder()
                .nombre("Juan Manuel")
                .apellido("Isaza")
                .especialidad("Cardiólogo")
                .telefono("312458903")
                .email("juanm.isazav@gmail.com")
                .build();
    }

    /**
     * Metodo que quema los datos de un paciente
     * @return Paciente con datos quemados
     */
    public static Paciente quemarPaciente() {
        return Paciente.builder()
                .nombre("Carlos")
                .apellido("Ramírez")
                .fechaNacimiento(LocalDate.of(1980, 5, 20))
                .genero("Masculino")
                .direccion("Calle 123 #45-67, Bogotá")
                .telefono("3104567890")
                .email("carlos.ramirez@example.com")
                .build();
    }
}
