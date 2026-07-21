package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.Genero;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class DocenteTest {
    @Test
    void verProgreso() {
        Date fechaPrueba = new Date();
        Usuario docente = new Docente("Ing. Lara", "lara@uce.edu.ec", "abcd", fechaPrueba, Genero.F, "Sistemas");
        assertDoesNotThrow(() -> docente.verProgreso(), "El método verProgreso() en Docente no debería lanzar ninguna excepción.");
    }
    @Test
    void getEspecialidad() {
        Date fechaPrueba = new Date();
        Docente docente = new Docente("Ing. Lara", "lara@uce.edu.ec", "abcd", fechaPrueba, Genero.F, "Cálculo");
        assertEquals("Cálculo", docente.getEspecialidad(), "La especialidad obtenida no coincide con la asignada en el constructor.");
    }

    @Test
    void setEspecialidad() {
        Date fechaPrueba = new Date();
        Docente docente = new Docente("Ing. Lara", "lara@uce.edu.ec", "abcd", fechaPrueba, Genero.F, "Álgebra");
        docente.setEspecialidad("Física Cuántica");
        assertEquals("Física Cuántica", docente.getEspecialidad(), "La especialidad no se modificó correctamente con el setter.");
    }

    @Test
    void testToString() {
        Date fechaPrueba = new Date();
        Docente docente = new Docente("Dr. Espinoza", "espinoza@uce.edu.ec", "9876", fechaPrueba,Genero.M, "Estructuras de Datos");

        String resultadoToString = docente.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        assertTrue(resultadoToString.contains("Dr. Espinoza"), "El toString() no incluye el nombre del docente.");
        assertTrue(resultadoToString.contains("Estructuras de Datos"), "El toString() no incluye la especialidad.");
    }
}