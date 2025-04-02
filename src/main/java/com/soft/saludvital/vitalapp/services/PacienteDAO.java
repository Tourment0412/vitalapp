package com.soft.saludvital.vitalapp.services;

public class PacienteDAO {

    private static PacienteDAO instance;
    private static final String RUTA= "data/paciente.dat";


    public static PacienteDAO getInstance() {
        if (instance == null) {
            instance = new PacienteDAO();
        }
        return instance;
    }
}
