package com.soft.saludvital.vitalapp.application;

import com.soft.saludvital.vitalapp.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Medico medico = quemarMedico();
        Paciente paciente = quemarPaciente();
        HistorialMedico historial = new HistorialMedico(paciente, null);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Paciente");
            System.out.println("2. Médico");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcionPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcionPrincipal) {
                case 1 -> menuPaciente(scanner, paciente, historial);
                case 2 -> menuMedico(scanner, medico, paciente, historial);
                case 3 -> {
                    System.out.println("¡Gracias por usar SaludVital!");
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }


    private static void menuPaciente(Scanner scanner, Paciente pacienteActual, HistorialMedico historial) {
        while (true) {
            System.out.println("\n--- MENU PACIENTE ---");
            System.out.println("1. Ver historial del paciente actual");
            System.out.println("2. Listar registros");
            System.out.println("3. Generar alerta");
            System.out.println("4. Registrar nuevo paciente");
            System.out.println("5. Listar todos los pacientes");
            System.out.println("6. Buscar paciente por ID");
            System.out.println("7. Actualizar paciente");
            System.out.println("8. Eliminar paciente");
            System.out.println("9. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    List<Cita> historialCitas = pacienteActual.getHistorialMedico().obtenerHistorial();
                    if (historialCitas != null) {
                        historialCitas.forEach(System.out::println);
                    } else {
                        System.out.println("No hay historial disponible.");
                    }
                }
                case 2 -> pacienteActual.getHistorialMedico().listarRegistros();
                case 3 -> {
                    String alerta = pacienteActual.generarAlerta();
                    System.out.println(alerta);
                    pacienteActual.enviarAlerta();
                }
                case 4 -> {
                    Paciente nuevo = crearPacienteDesdeInput(scanner);
                    if (Paciente.registrarPaciente(nuevo)) {
                        System.out.println("✅ Paciente registrado exitosamente.");
                    } else {
                        System.out.println("❌ No se pudo registrar el paciente (puede que ya exista).");
                    }
                }
                case 5 -> {
                    List<Paciente> pacientes = Paciente.obtenerListaPacientes();
                    if (pacientes.isEmpty()) {
                        System.out.println("No hay pacientes registrados.");
                    } else {
                        pacientes.forEach(System.out::println);
                    }
                }
                case 6 -> {
                    System.out.print("Ingrese el ID del paciente: ");
                    String id = scanner.nextLine();
                    Paciente buscado = Paciente.obtenerPaciente(id);
                    System.out.println(buscado != null ? buscado : "❌ Paciente no encontrado.");
                }
                case 7 -> {
                    System.out.print("Ingrese el ID del paciente a actualizar: ");
                    String id = scanner.nextLine();
                    Paciente existente = Paciente.obtenerPaciente(id);
                    if (existente != null) {
                        Paciente actualizado = crearPacienteDesdeInput(scanner);
                        actualizado.setId(id); // mantener el mismo ID
                        if (Paciente.actualizarPaciente(id, actualizado)) {
                            System.out.println("✅ Paciente actualizado correctamente.");
                        } else {
                            System.out.println("❌ Error al actualizar el paciente.");
                        }
                    } else {
                        System.out.println("❌ Paciente no encontrado.");
                    }
                }
                case 8 -> {
                    System.out.print("Ingrese el ID del paciente a eliminar: ");
                    String id = scanner.nextLine();
                    boolean eliminado = Paciente.eliminarPaciente(id);
                    System.out.println(eliminado ? "✅ Paciente eliminado." : "❌ Paciente no encontrado.");
                }
                case 9 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static Paciente crearPacienteDesdeInput(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        return Paciente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(fechaNacimiento)
                .genero(genero)
                .direccion(direccion)
                .telefono(telefono)
                .email(email)
                .build();
    }


    private static void menuMedico(Scanner scanner, Medico medico, Paciente paciente, HistorialMedico historial) {
        while (true) {
            System.out.println("\n--- MENU MEDICO ---");
            System.out.println("1. Agendar cita");
            System.out.println("2. Actualizar cita");
            System.out.println("3. Eliminar cita");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Estado: ");
                    String estado = scanner.nextLine();
                    System.out.print("Observaciones: ");
                    String observaciones = scanner.nextLine();
                    System.out.print("Año (YYYY): ");
                    int anio = scanner.nextInt();
                    System.out.print("Mes (1-12): ");
                    int mes = scanner.nextInt();
                    System.out.print("Día: ");
                    int dia = scanner.nextInt();
                    System.out.print("Hora (0-23): ");
                    int hora = scanner.nextInt();
                    System.out.print("Minuto: ");
                    int minuto = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    LocalDateTime fechaHora = LocalDateTime.of(anio, mes, dia, hora, minuto);
                    Cita cita = medico.agendarCita(fechaHora, paciente, descripcion, estado, observaciones);
                    historial.agregarRegistro(cita);
                    System.out.println("✅ Cita agendada exitosamente.");
                }
                case 2 -> {
                    System.out.print("ID de la cita a actualizar: ");
                    String id = scanner.nextLine();
                    System.out.print("Nuevo diagnóstico: ");
                    String diagnostico = scanner.nextLine();
                    System.out.print("Nueva gravedad (Leve/Grave/Urgente): ");
                    String gravedad = scanner.nextLine();

                    Cita cita = buscarCita(historial, id);
                    if (cita != null) {
                        cita.definirDiagnostico(gravedad, diagnostico);
                        boolean actualizado = historial.actualizarRegistro(cita);
                        System.out.println(actualizado ? "✅ Cita actualizada." : "❌ No se pudo actualizar.");
                    } else {
                        System.out.println("❌ Cita no encontrada.");
                    }
                }
                case 3 -> {
                    System.out.print("ID de la cita a eliminar: ");
                    String id = scanner.nextLine();
                    boolean eliminada = historial.eliminarRegistro(id);
                    System.out.println(eliminada ? "✅ Cita eliminada." : "❌ No se encontró la cita.");
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static Cita buscarCita(HistorialMedico historial, String id) {
        List<Cita> citas = historial.obtenerHistorial();
        if (citas != null) {
            for (Cita cita : citas) {
                if (cita.getId().equals(id)) {
                    return cita;
                }
            }
        }
        return null;
    }

    public static Medico quemarMedico() {
        return Medico.builder()
                .nombre("Juan Manuel")
                .apellido("Isaza")
                .especialidad("Cardiólogo")
                .telefono("312458903")
                .email("juanm.isazav@gmail.com")
                .build();
    }

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
