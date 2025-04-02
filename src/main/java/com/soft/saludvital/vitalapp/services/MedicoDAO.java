package com.soft.saludvital.vitalapp.services;

public class MedicoDAO {

    private static MedicoDAO instance;
    private static final String RUTA= "data/medico.dat";


    public static MedicoDAO getInstance() {
        if (instance == null) {
            instance = new MedicoDAO();
        }
        return instance;
    }
}
