package com.soft.saludvital.vitalapp.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/*
    Historial medico
 */
public class HistorialMedico {
    @EqualsAndHashCode.Include
    private String id;
    private Paciente paciente;
    private List<Cita> listaCitas;

    @Builder
    public HistorialMedico(Paciente paciente,  List<Cita> listaCitas) {
        this.paciente = paciente;
        this.listaCitas = listaCitas;
    }

    /**
     * Metodo que agrega una cita al historial del un paciente
     *
     * @param cita cita nueva a agregar
     * @return
     */
    public boolean agregarRegistro(Cita cita) {
        if (listaCitas == null) {
            listaCitas = new ArrayList<>();
        }
        listaCitas.add(cita);

        return true;
    }

    /**
     * Metodo que actualiza una cita en especifica del historial
     * @param cita cita con la se reemplazara la anterior
     * @return true si se hizo la actualizacion, false en caso contrario
     */
    public boolean actualizarRegistro( Cita cita){
        if(listaCitas!=null){
            for(int i=0;i<listaCitas.size();i++){
                if(listaCitas.get(i).getId().equals(cita.getId())){
                    listaCitas.set(i,cita);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo que obtiene todo el historial de citas del un paciente
     * @return historial de paciente
     */
    public List<Cita> obtenerHistorial(){
        return listaCitas;
    }

    /**
     * Metodo que muestra una a una las citas del hitorial del paciente
     */
    public void listarRegistros(){
        if(listaCitas == null || listaCitas.isEmpty()){
            System.out.println("No hay registros en el historial.");
        } else {
            listaCitas.forEach(System.out::println);
        }
    }

    /**
     * Metodo que elimina un registro del historial del paciente
     * @param id id del registro/cita a eliminar
     * @return true si se elimino correctamente, false en caso contrario
     */
    public boolean eliminarRegistro(String id){
        if(listaCitas!=null){
            for(int i=0;i<listaCitas.size();i++){
                if(listaCitas.get(i).getId().equals(id)){
                    listaCitas.remove(i);
                    return true;
                }
            }
        }
        return false;
    }


    public String toString() {
        return "HistorialMedico{" +
                "id=" + id +
                ", registros=" + listaCitas +
                // No llamar a paciente.toString()
                ", paciente=" + (paciente != null ? paciente.getNombre() + " " + paciente.getApellido() : "null") +
                '}';
    }


}
