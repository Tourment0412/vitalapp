package com.soft.saludvital.vitalapp.services;

public class CitaDAO {

    private static CitaDAO instance;
    private static final String RUTA= "data/cita.dat";


    public static CitaDAO getInstance() {
        if (instance == null) {
            instance = new CitaDAO();
        }
        return instance;
    }
}
