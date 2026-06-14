package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {
    @Test
    void verProgreso() {
        Date fechaPrueba = new Date();
        Progreso progresoSimulado = new Progreso();
        Usuario estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", progresoSimulado);

        assertDoesNotThrow(() -> estudiante.verProgreso(), "El método verProgreso() en Estudiante no debería lanzar ninguna excepción.");
    }
    @Test
    void getNivel() {
        Date fechaPrueba = new Date();
        Progreso progresoSimulado = new Progreso();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", progresoSimulado);

        assertEquals("Principiante", estudiante.getNivel(), "El nivel obtenido no coincide con el asignado en el constructor.");
    }

    @Test
    void setLevel() {
        Date fechaPrueba = new Date();
        Progreso progresoSimulado = new Progreso();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", progresoSimulado);

        estudiante.setNivel("Avanzado");

        assertEquals("Avanzado", estudiante.getNivel(), "El nivel no se modificó correctamente con el método setLevel.");
    }

    @Test
    void getProgreso() {
        Date fechaPrueba = new Date();
        Progreso progresoSimulado = new Progreso();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", progresoSimulado);

        assertNotNull(estudiante.getProgreso(), "El progreso obtenido no debería ser nulo.");
        assertEquals(progresoSimulado, estudiante.getProgreso(), "El objeto Progreso obtenido no coincide con el instanciado.");
    }

    @Test
    void setProgreso() {
        Date fechaPrueba = new Date();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", null);

        Progreso nuevoProgreso = new Progreso();
        estudiante.setProgreso(nuevoProgreso);

        assertNotNull(estudiante.getProgreso(), "El progreso no se asignó correctamente con el setter.");
        assertEquals(nuevoProgreso, estudiante.getProgreso(), "El objeto Progreso modificado no coincide.");
    }

    @Test
    void testToString() {
        Date fechaPrueba = new Date();
        Progreso progresoSimulado = new Progreso();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Experto", progresoSimulado);

        String resultadoToString = estudiante.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        assertTrue(resultadoToString.contains("Jeremy"), "El toString() no incluye el nombre del estudiante.");
        assertTrue(resultadoToString.contains("Experto"), "El toString() no incluye el nivel del estudiante.");
    }
}