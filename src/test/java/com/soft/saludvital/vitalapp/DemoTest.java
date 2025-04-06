package com.soft.saludvital.vitalapp;

import com.soft.saludvital.vitalapp.model.Cita;
import com.soft.saludvital.vitalapp.model.Medico;
import com.soft.saludvital.vitalapp.model.Paciente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {

    /**
     * Metodo que quema los datos de un medico
     * @return Medico con datos quemados
     */
    public Medico quemarMedico() {
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
    public Paciente quemarPaciente() {
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

    /**
     * Metodo que quema los datos de una cita
     * @return Cita con datos quemados
     */
    public Cita quemarCita(Medico medico,Paciente paciente) {
        return medico.agendarCita(
                LocalDateTime.of(2025, 4, 10, 15, 30),
                paciente,
                "Chequeo cardiologico de rutina",
                "Programada",
                "Paciente indica fatiga al caminar");
    }

    /**
     *  Metodo que verifica el funcionamiento del agendamiento de citas
     */
    @Test
    void agendarCitaTest() {
        Medico medico = quemarMedico();
        Paciente paciente = quemarPaciente();
        LocalDateTime fechaHora=LocalDateTime.of(2025, 4, 10, 15, 30);
        Cita cita=medico.agendarCita(
                fechaHora,
                paciente,
                "Chequeo cardiologico de rutina",
                "Programada",
                "Paciente indica fatiga al caminar");
        assertAll("Verificar datos de la cita",
                () -> assertEquals(medico, cita.getMedico()),
                () -> assertEquals(paciente, cita.getPaciente()),
                () -> assertEquals(fechaHora, cita.getFechaHora()),
                () -> assertEquals("Chequeo cardiologico de rutina", cita.getMotivo()),
                () -> assertEquals("Programada", cita.getEstado()),
                () -> assertEquals("Paciente indica fatiga al caminar", cita.getObservaciones())
        );
    }
}
