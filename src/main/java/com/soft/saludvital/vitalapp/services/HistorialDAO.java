package com.soft.saludvital.vitalapp.services;

public class HistorialDAO {

    private static HistorialDAO instance;
    private static final String RUTA= "data/historial.dat";


    public static HistorialDAO getInstance() {
        if (instance == null) {
            instance = new HistorialDAO();
        }
        return instance;
    }
}
