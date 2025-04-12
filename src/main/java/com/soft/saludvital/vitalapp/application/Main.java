
package com.soft.saludvital.vitalapp.application;

import com.soft.saludvital.vitalapp.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    // Formateador para fechas y horas
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        imprimirEncabezado("SISTEMA DE GESTIÓN DE SALUD VITAL");

        // ==================== CREACIÓN DE PACIENTES ====================
        imprimirTitulo("1. Registro de Pacientes");

        Paciente paciente1 = crearPaciente1();
        Paciente paciente2 = crearPaciente2();

        registrarPacientes(paciente1, paciente2);

        // ==================== CREACIÓN DE MÉDICOS ====================
        imprimirTitulo("2. Registro de Médicos");

        Medico medico1 = crearMedico1();
        Medico medico2 = crearMedico2();

        // Podríamos tener un método para registrar médicos similar a pacientes
        imprimirInformacionMedico(medico1);
        imprimirInformacionMedico(medico2);

        // ==================== GESTIÓN DE CITAS ====================
        imprimirTitulo("3. Gestión de Citas");

        Cita cita1 = crearCita1(paciente1, medico1);
        Cita cita2 = crearCita2(paciente1, medico2);

        // Agregar citas al historial
        agregarCitasAHistorial(paciente1, cita1, cita2);

        // ==================== MOSTRAR HISTORIAL INICIAL ====================
        imprimirTitulo("4. Historial Médico Inicial");
        mostrarHistorialPaciente(paciente1, "antes de finalización de citas");

        // ==================== ACTUALIZACIÓN DE DIAGNÓSTICOS ====================
        imprimirTitulo("5. Actualización de Diagnósticos");

        actualizarDiagnosticos(cita1, cita2);
        mostrarHistorialPaciente(paciente1, "después de finalización de citas");

        // ==================== ENVÍO DE ALERTAS ====================
        imprimirTitulo("6. Sistema de Alertas");

        try {
            enviarAlerta(paciente1);
        } catch (Exception e) {
            System.out.println("Error al enviar alerta: " + e.getMessage());
        }

        // ==================== ACTUALIZACIÓN DE CITAS ====================
        imprimirTitulo("7. Actualización de Información");

        Cita citaActualizada = actualizarCita(paciente1, medico1);
        mostrarResultadoOperacion("Actualización de cita",
                paciente1.getHistorialMedico().actualizarRegistro(citaActualizada));

        mostrarHistorialPaciente(paciente1, "después de actualización");

        // ==================== ELIMINACIÓN DE REGISTROS ====================
        imprimirTitulo("8. Eliminación de Registros");

        mostrarResultadoOperacion("Eliminación de cita (ID: " + cita1.getId() + ")",
                paciente1.getHistorialMedico().eliminarRegistro(cita1.getId()));

        mostrarHistorialPaciente(paciente1, "después de eliminación de cita");

        // Eliminar paciente
        mostrarResultadoOperacion("Eliminación de paciente " + paciente2.getNombre() + " " + paciente2.getApellido(),
                Paciente.eliminarPaciente(paciente2.getId()));

        // ==================== RESUMEN FINAL ====================
        imprimirTitulo("9. Resumen Final del Sistema");

        System.out.println("Pacientes actualmente registrados en el sistema:");
        mostrarListaPacientes();

        imprimirPieDePagina("FIN DE LA DEMOSTRACIÓN");
    }

    // ======== MÉTODOS DE CREACIÓN ========

    private static Paciente crearPaciente1() {
        Paciente paciente = Paciente.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .fechaNacimiento(LocalDate.of(1990, 5, 12))
                .genero("Masculino")
                .direccion("Calle 123")
                .telefono("3001234567")
                .email("juanperez@gmail.com")
                .build();

        imprimirInformacionPaciente(paciente);
        return paciente;
    }

    private static Paciente crearPaciente2() {
        Paciente paciente = Paciente.builder()
                .nombre("María")
                .apellido("Gómez")
                .fechaNacimiento(LocalDate.of(1985, 9, 23))
                .genero("Femenino")
                .direccion("Carrera 45")
                .telefono("3007654321")
                .email("mariagomez@hotmail.com")
                .build();

        imprimirInformacionPaciente(paciente);
        return paciente;
    }

    private static Medico crearMedico1() {
        return Medico.builder()
                .nombre("Juan Manuel")
                .apellido("Isaza")
                .especialidad("Cardiólogo")
                .telefono("312458903")
                .email("juanm.isazav@gmail.com")
                .build();
    }

    private static Medico crearMedico2() {
        return Medico.builder()
                .nombre("Miguel")
                .apellido("Mira")
                .especialidad("General")
                .telefono("3223553281")
                .email("miguela.mira@gmail.com")
                .build();
    }

    private static Cita crearCita1(Paciente paciente, Medico medico) {
        Cita cita = Cita.builder()
                .paciente(paciente)
                .medico(medico)
                .fechaHora(LocalDateTime.now().minusDays(10))
                .motivo("Chequeo cardiológico de rutina")
                .estado("leve")
                .observaciones("Paciente con tensión alta")
                .build();

        System.out.println("✓ Cita creada: " + cita.getId() + " para " +
                paciente.getNombre() + " con Dr. " + medico.getApellido() +
                " - Motivo: " + cita.getMotivo());
        return cita;
    }

    private static Cita crearCita2(Paciente paciente, Medico medico) {
        Cita cita = Cita.builder()
                .paciente(paciente)
                .medico(medico)
                .fechaHora(LocalDateTime.now().minusDays(2))
                .motivo("Dolor abdominal fuerte")
                .estado("grave")
                .observaciones("Paciente tiene dolor abdominal fuerte")
                .build();

        System.out.println("✓ Cita creada: " + cita.getId() + " para " +
                paciente.getNombre() + " con Dr. " + medico.getApellido() +
                " - Motivo: " + cita.getMotivo());
        return cita;
    }

    private static Cita actualizarCita(Paciente paciente, Medico medico) {
        Cita cita = Cita.builder()
                .paciente(paciente)
                .medico(medico)
                .fechaHora(LocalDateTime.now().minusDays(1))
                .motivo("Dolor abdominal leve")
                .estado("no grave")
                .observaciones("Paciente tiene dolor abdominal medio")
                .build();

        System.out.println("✓ Cita actualizada para: " + paciente.getNombre() +
                " con Dr. " + medico.getApellido() +
                " - Nuevo estado: " + cita.getEstado());
        return cita;
    }

    // ======== MÉTODOS DE OPERACIÓN ========

    private static void registrarPacientes(Paciente... pacientes) {
        System.out.println("\nRegistrando pacientes en el sistema...");
        for (Paciente p : pacientes) {
            boolean resultado = Paciente.registrarPaciente(p);
            System.out.println("✓ Registro de " + p.getNombre() + " " + p.getApellido() +
                    ": " + (resultado ? "EXITOSO" : "FALLIDO"));
        }
    }

    private static void agregarCitasAHistorial(Paciente paciente, Cita... citas) {
        System.out.println("\nAgregando citas al historial del paciente " +
                paciente.getNombre() + " " + paciente.getApellido() + "...");

        for (Cita cita : citas) {
            boolean resultado = paciente.getHistorialMedico().agregarRegistro(cita);
            System.out.println("✓ Cita " + cita.getId() + " agregada: " +
                    (resultado ? "EXITOSO" : "FALLIDO"));
        }
    }

    private static void actualizarDiagnosticos(Cita cita1, Cita cita2) {
        System.out.println("Actualizando diagnósticos de las citas...");

        cita1.definirDiagnostico("grave", "El paciente sufre problemas leves del corazón, " +
                "es necesario agendar una cita pronto con especialista");
        System.out.println("✓ Diagnóstico actualizado para cita " + cita1.getId() +
                " - Estado: " + cita1.getEstado());

        cita2.definirDiagnostico("urgente", "El paciente tiene dolor abdominal fuerte");
        System.out.println("✓ Diagnóstico actualizado para cita " + cita2.getId() +
                " - Estado: " + cita2.getEstado());
    }

    private static void enviarAlerta(Paciente paciente) {
        System.out.println("Enviando alerta de salud para paciente " +
                paciente.getNombre() + " " + paciente.getApellido() + "...");
        paciente.enviarAlerta();
        System.out.println("✓ Alerta enviada exitosamente");
    }

    // ======== MÉTODOS DE VISUALIZACIÓN ========

    private static void imprimirInformacionPaciente(Paciente paciente) {
        System.out.println("→ Paciente: " + paciente.getNombre() + " " + paciente.getApellido() +
                " | Nacimiento: " + paciente.getFechaNacimiento().format(DATE_FORMATTER) +
                " | Contacto: " + paciente.getTelefono());
    }

    private static void imprimirInformacionMedico(Medico medico) {
        System.out.println("→ Dr. " + medico.getNombre() + " " + medico.getApellido() +
                " | Especialidad: " + medico.getEspecialidad() +
                " | Contacto: " + medico.getTelefono());
    }

    private static void mostrarHistorialPaciente(Paciente paciente, String contexto) {
        System.out.println("\nHistorial médico de " + paciente.getNombre() + " " +
                paciente.getApellido() + " (" + contexto + "):");

        if (paciente.getHistorialMedico().getListaCitas().isEmpty()) {
            System.out.println("   [El historial está vacío]");
            return;
        }

        System.out.println("   ID Cita  |  Fecha        |  Médico          |  Estado   |  Motivo");
        System.out.println("   ------------------------------------------------------------------");

        paciente.getHistorialMedico().getListaCitas().forEach(registro -> {
            if (registro instanceof Cita) {
                Cita cita = (Cita) registro;
                System.out.printf("   %-8s | %-12s | Dr. %-12s | %-8s | %s%n",
                        cita.getId(),
                        cita.getFechaHora().format(DATE_TIME_FORMATTER),
                        cita.getMedico().getApellido(),
                        cita.getEstado(),
                        cita.getMotivo()
                );
            }
        });
    }

    private static void mostrarListaPacientes() {
        if (Paciente.obtenerListaPacientes().isEmpty()) {
            System.out.println("   [No hay pacientes registrados]");
            return;
        }

        System.out.println("   ID     |  Nombre Completo     |  Contacto");
        System.out.println("   ------------------------------------------");

        Paciente.obtenerListaPacientes().forEach(p ->
                System.out.printf("   %-6s | %-20s | %s%n",
                        p.getId(),
                        p.getNombre() + " " + p.getApellido(),
                        p.getTelefono()
                )
        );
    }

    private static void mostrarResultadoOperacion(String operacion, boolean resultado) {
        String icono = resultado ? "✓" : "✗";
        String estado = resultado ? "EXITOSO" : "FALLIDO";
        System.out.println(icono + " " + operacion + ": " + estado);
    }

    // ======== MÉTODOS DE FORMATO ========

    private static void imprimirEncabezado(String titulo) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(" " + titulo);
        System.out.println("=".repeat(80));
    }

    private static void imprimirTitulo(String titulo) {
        System.out.println("\n" + "-".repeat(80));
        System.out.println(" " + titulo);
        System.out.println("-".repeat(80));
    }

    private static void imprimirPieDePagina(String mensaje) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(" " + mensaje);
        System.out.println("=".repeat(80) + "\n");
    }
}