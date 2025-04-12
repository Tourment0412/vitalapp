package com.soft.saludvital.vitalapp.model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private static final List<Paciente> listaPacientes = new ArrayList<>();

    @Builder
    public Paciente(String nombre, String apellido, LocalDate fechaNacimiento, String genero, String direccion, String telefono, String email ){
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
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


    /**
     * Metodo que registra un nuevo paciente en la lista de pacientes si este no es nulo
     * y no existe ya un paciente con el mismo ID en la lista.
     *
     * @param paciente el objeto Paciente que se desea registrar
     * @return true si el paciente se registra exitosamente, false en caso contrario
     */
    public static boolean registrarPaciente(Paciente paciente){
        if(paciente!=null  && obtenerPaciente(paciente.getId())==null){
            return listaPacientes.add(paciente);
        }
        return false;
    }

    /**
     * Metodo que busca y obtiene un paciente con el ID proporcionado de la lista de pacientes.
     *
     * @param idPaciente el identificador del paciente a buscar
     * @return el objeto Paciente si se encuentra un paciente con el ID especificado,
     *         o null si no se encuentra ningún paciente con dicho ID
     */
    public static Paciente obtenerPaciente(String idPaciente){
        for(Paciente paciente:listaPacientes){
            if(paciente.getId().equals(idPaciente)){
                return paciente;
            }
        }
        return null;
    }

    /**
     * Metodo que devuelve una copia de la lista de pacientes almacenada.
     *
     * @return una lista que contiene todos los pacientes registrados.
     */
    public static List<Paciente> obtenerListaPacientes(){
        return new ArrayList<>(listaPacientes);
    }

    /**
     * Metodo que elimina un paciente de la lista de pacientes si su ID coincide.
     *
     * @param id el identificador del paciente que se desea eliminar
     * @return true si el paciente fue eliminado exitosamente, false si no se encontró un paciente con el ID especificado
     */
    public static boolean eliminarPaciente(String id){
        for(int i=0;i<listaPacientes.size();i++){
            if(listaPacientes.get(i).getId().equals(id)){
                listaPacientes.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que actualiza la informacion de un paciente existente en la lista
     * de pacientes con un nuevo objeto de paciente.
     *
     * @param id identificador del paciente que se desea actualizar
     * @param pacienteActualizado objeto Paciente con la informacion actualizada
     *        que reemplazara al paciente existente
     * @return true si el paciente fue encontrado en la lista y actualizado
     *         correctamente, false si no se encontró un paciente con el identificador especificado
     */
    public static boolean actualizarPaciente(String id,Paciente pacienteActualizado){
        for(int i=0;i<listaPacientes.size();i++){
            if(listaPacientes.get(i).getId().equals(id)){
                listaPacientes.set(i,pacienteActualizado);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", genero='" + genero + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                // Evitar imprimir historial completo
                ", historialMedico=" + (historialMedico != null ? "Historial con " + historialMedico.getListaCitas().size() + " registro(s)" : "null") +
                '}';
    }



}
