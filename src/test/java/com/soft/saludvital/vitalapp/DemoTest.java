package com.soft.saludvital.vitalapp;

import com.soft.saludvital.vitalapp.model.Cita;
import com.soft.saludvital.vitalapp.model.Medico;
import com.soft.saludvital.vitalapp.model.Paciente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Metodo de prueba que verifica el correcto funcionamiento del flujo de definicion de un diagnostico
     * asociado a una cita medica en el sistema.
     *
     * Este metodo realiza las siguientes tareas:
     * 1. Crea un objeto de tipo Medico utilizando el metodo quemarMedico.
     * 2. Crea un objeto de tipo Paciente utilizando el metodo quemarPaciente.
     * 3. Agenda una cita medica asociada al medico y paciente, especificando una fecha, motivo, estado,
     *    y observaciones, utilizando el metodo Medico.agendarCita.
     * 4. Define un diagnostico para la cita, incluyendo un nivel de gravedad y una descripcion detallada,
     *    mediante el metodo Cita.definirDiagnostico.
     * 5. Realiza multiples comprobaciones mediante aserciones:
     *    - Verifica que los datos de la cita, como el medico, paciente, fecha/hora, motivo, estado, y
     *      observaciones sean correctos.
     *    - Confirma que el diagnostico y la gravedad definida estan correctamente establecidos.
     */
    @Test
    void difinirDiagnosticoTest() {
        Medico medico = quemarMedico();
        Paciente paciente = quemarPaciente();
        LocalDateTime fechaHora=LocalDateTime.of(2025, 4, 10, 15, 30);
        Cita cita=medico.agendarCita(
                fechaHora,
                paciente,
                "Chequeo cardiologico de rutina",
                "Programada",
                "Paciente indica fatiga al caminar");
        cita.definirDiagnostico("Urgente","El paciente sufre problemas severos del del corazon, es necesario agendar una cita urgente con especialista");
        assertAll("Verificar datos de la cita",
                () -> assertEquals(medico, cita.getMedico()),
                () -> assertEquals(paciente, cita.getPaciente()),
                () -> assertEquals(fechaHora, cita.getFechaHora()),
                () -> assertEquals("Chequeo cardiologico de rutina", cita.getMotivo()),
                () -> assertEquals("Programada", cita.getEstado()),
                () -> assertEquals("Paciente indica fatiga al caminar", cita.getObservaciones()),
                () -> assertEquals("Urgente", cita.getGravedadDiagnostigo()),
                () -> assertEquals("El paciente sufre problemas severos del del corazon, es necesario agendar una cita urgente con especialista", cita.getDiagnostico())
        );
    }

    /**
     * Metodo de prueba que verifica el correcto funcionamiento del flujo de envio de diagnosticos
     * asociado a una cita medica en el sistema.
     *
     * El metodo realiza las siguientes tareas:
     * 1. Crea objetos de tipo Medico y Paciente utilizando los metodos quemarMedico y quemarPaciente.
     * 2. Agenda una cita medica asociada al medico y paciente, con una fecha, motivo, estado, y
     *    observaciones especificas, utilizando el metodo Medico.agendarCita.
     * 3. Define un diagnostico para la cita con un nivel de gravedad y una descripcion detallada
     *    utilizando el metodo Cita.definirDiagnostico.
     * 4. Genera una alerta para el paciente basada en el nivel de gravedad del diagnostico
     *    utilizando el metodo Paciente.generarAlerta.
     * 5. Realiza multiples comprobaciones mediante aserciones:
     *    - Verifica que los datos de la cita coincidan con los valores asignados.
     *    - Valida la asociacion de medico y paciente en la cita.
     *    - Confirma que las observaciones, motivo, estado, y diagnostico de la cita sean
     *      correctos.
     *    - Asegura que la alerta generada para el paciente contenga la palabra "URGENTE" cuando
     *      corresponda.
     */
    @Test
    void enviarDiagnosticoTest() {
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

        cita.definirDiagnostico("Urgente", "El paciente sufre problemas severos del del corazon, es necesario agendar una cita urgente con especialista");

        String alerta = paciente.generarAlerta();

        assertAll("Verificar datos de la cita y alerta",
                () -> assertEquals(medico, cita.getMedico()),
                () -> assertEquals(paciente, cita.getPaciente()),
                () -> assertEquals(fechaHora, cita.getFechaHora()),
                () -> assertEquals("Chequeo cardiologico de rutina", cita.getMotivo()),
                () -> assertEquals("Programada", cita.getEstado()),
                () -> assertEquals("Paciente indica fatiga al caminar", cita.getObservaciones()),
                () -> assertEquals("Urgente", cita.getGravedadDiagnostigo()),
                () -> assertEquals("El paciente sufre problemas severos del del corazon, es necesario agendar una cita urgente con especialista", cita.getDiagnostico()),
                () -> assertTrue(alerta.contains("URGENTE"))
        );
    }

    /**
     * Metodo de prueba que verifica el correcto funcionamiento del registro de un nuevo paciente
     * en el sistema.
     *
     * El metodo realiza las siguientes tareas:
     * 1. Crea un nuevo objeto de tipo Paciente utilizando el metodo quemarPaciente.
     * 2. Registra el paciente utilizando el metodo Paciente.registrarPaciente(Paciente).
     * 3. Verifica que el paciente fue registrado correctamente comparando el resultado con true.
     * 4. Confirma que el paciente se encuentra en la lista de pacientes registrada
     *    utilizando el metodo Paciente.obtenerListaPacientes().
     *
     * Se realizan las verificaciones utilizando las aserciones assertTrue.
     */
    @Test
    void registrarPacienteTest() {
        Paciente paciente = quemarPaciente();
        boolean registrado = Paciente.registrarPaciente(paciente);
        assertTrue(registrado);
        assertTrue(Paciente.obtenerListaPacientes().contains(paciente));
    }

    /**
     * Metodo de prueba que verifica el correcto funcionamiento del proceso de actualizacion
     * de los datos de un paciente en el sistema.
     *
     * Este metodo realiza las siguientes tareas:
     * 1. Crea un objeto de tipo Paciente con datos iniciales utilizando el metodo quemarPaciente.
     * 2. Registra el paciente en el sistema utilizando el metodo Paciente.registrarPaciente.
     * 3. Construye un objeto Paciente actualizado con nuevos datos (nombre, direccion, etc.).
     * 4. Establece el mismo ID del paciente original en el objeto actualizado.
     * 5. Llama al metodo Paciente.actualizarPaciente para realizar la actualizacion en el sistema.
     * 6. Realiza comprobaciones mediante aserciones:
     *    - Confirma que la actualizacion del paciente regresa un resultado positivo (true).
     *    - Verifica que los valores actualizados, como el nombre y la direccion,
     *      coincidan con los datos presentes en el sistema.
     */
    @Test
    void actualizarPacienteTest() {
        Paciente original = quemarPaciente();
        Paciente.registrarPaciente(original);

        Paciente actualizado = Paciente.builder()
                .nombre("Carlos Andrés")
                .apellido("Ramírez")
                .fechaNacimiento(LocalDate.of(1980, 5, 20))
                .genero("Masculino")
                .direccion("Nueva dirección")
                .telefono("3100000000")
                .email("nuevo.email@example.com")
                .build();
        actualizado.setId(original.getId());

        boolean resultado = Paciente.actualizarPaciente(original.getId(), actualizado);
        assertTrue(resultado);
        assertEquals("Carlos Andrés", Paciente.obtenerPaciente(original.getId()).getNombre());
        assertEquals("Nueva dirección", Paciente.obtenerPaciente(original.getId()).getDireccion());
    }

    /**
     * Metodo de prueba que verifica el correcto funcionamiento del proceso de eliminacion
     * de un paciente en el sistema.
     *
     * Este metodo realiza las siguientes tareas:
     * 1. Crea un objeto de tipo Paciente utilizando el metodo quemarPaciente.
     * 2. Registra el paciente en el sistema mediante el metodo Paciente.registrarPaciente.
     * 3. Llama al metodo Paciente.eliminarPaciente para eliminar el paciente segun su ID.
     * 4. Realiza comprobaciones mediante aserciones:
     *    - Confirma que el metodo de eliminacion devuelve un resultado positivo (true).
     *    - Verifica que el paciente eliminado ya no puede ser obtenido del sistema
     *      utilizando el metodo Paciente.obtenerPaciente, asegurando que este devuelva null.
     */
    @Test
    void eliminarPacienteTest() {
        Paciente paciente = quemarPaciente();
        Paciente.registrarPaciente(paciente);
        boolean eliminado = Paciente.eliminarPaciente(paciente.getId());
        assertTrue(eliminado);
        assertNull(Paciente.obtenerPaciente(paciente.getId()));
    }

    /**
     * Metodo de prueba que verifica el correcto funcionamiento del proceso
     * para obtener un paciente del sistema utilizando su ID.
     *
     * Este metodo realiza las siguientes tareas:
     * 1. Crea un objeto de tipo Paciente con datos predeterminados utilizando el metodo quemarPaciente.
     * 2. Registra el paciente en el sistema mediante el metodo Paciente.registrarPaciente.
     * 3. Obtiene el paciente registrado utilizando el metodo Paciente.obtenerPaciente con el ID del paciente.
     * 4. Realiza las siguientes verificaciones mediante aserciones:
     *    - Confirma que el paciente encontrado no es nulo.
     *    - Verifica que el email del paciente encontrado coincide con el email del paciente registrado.
     */
    @Test
    void obtenerPacienteTest() {
        Paciente paciente = quemarPaciente();
        Paciente.registrarPaciente(paciente);
        Paciente encontrado = Paciente.obtenerPaciente(paciente.getId());
        assertNotNull(encontrado);
        assertEquals(paciente.getEmail(), encontrado.getEmail());
    }

    /**
     * Metodo de prueba que verifica el correcto funcionamiento del proceso
     * de agregar y obtener citas en el historial medico de un paciente.
     *
     * Este metodo realiza las siguientes tareas:
     * 1. Crea un objeto de tipo Paciente utilizando el metodo quemarPaciente.
     * 2. Crea un objeto de tipo Medico utilizando el metodo quemarMedico.
     * 3. Crea un objeto de tipo Cita asociada al medico y paciente utilizando
     *    el metodo quemarCita.
     * 4. Agrega la cita creada al historial medico del paciente mediante el metodo
     *    agregarRegistro.
     * 5. Obtiene el historial medico actualizado del paciente utilizando el metodo
     *    obtenerHistorial.
     * 6. Realiza las siguientes comprobaciones mediante aserciones:
     *    - Verifica que el historial no sea nulo.
     *    - Confirma que el historial contiene la cita previamente agregada.
     */
    @Test
    void historialMedicoAgregarYObtenerTest() {
        Paciente paciente = quemarPaciente();
        Medico medico = quemarMedico();
        Cita cita = quemarCita(medico, paciente);

        paciente.getHistorialMedico().agregarRegistro(cita);
        List<Cita> historial = paciente.getHistorialMedico().obtenerHistorial();

        assertNotNull(historial);
        assertTrue(historial.contains(cita));
    }


    /**
     * Metodo de prueba que verifica el correcto funcionamiento del sistema al listar
     * los registros del historial medico de un paciente.
     *
     * Este metodo realiza las siguientes tareas:
     * 1. Crea un objeto de tipo Paciente utilizando el metodo quemarPaciente.
     * 2. Crea un objeto de tipo Medico utilizando el metodo quemarMedico.
     * 3. Crea un objeto de tipo Cita asociada al medico y paciente, utilizando el metodo quemarCita.
     * 4. Agrega la cita al historial medico del paciente mediante el metodo agregarRegistro.
     * 5. Comprueba mediante una asercion que el historial medico del paciente no este vacio
     *    despues de agregar un registro.
     */
    @Test
    void historialMedicoListarRegistrosTest() {
        Paciente paciente = quemarPaciente();
        Medico medico = quemarMedico();
        Cita cita = quemarCita(medico, paciente);

        paciente.getHistorialMedico().agregarRegistro(cita);
        assertFalse(paciente.getHistorialMedico().obtenerHistorial().isEmpty());
    }





}
