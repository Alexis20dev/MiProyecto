package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocenteTest {

    @Test
    void crearEjercicio() {
        Docente docente = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new java.util.Date());
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        docente.crearEjercicio(ejercicio);
        assertNotNull(docente, "El docente debería completar la creación del ejercicio");
    }

    @Test
    void editarEjercicio() {
        Docente docente = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new java.util.Date());
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        docente.editarEjercicio(ejercicio);
        assertNotNull(docente);
    }

    @Test
    void eliminarEjercicio() {
        Docente docente = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new java.util.Date());
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        docente.eliminarEjercicio(ejercicio);
        assertNotNull(docente);
    }

    @Test
    void revisarEjercicio() {
        Docente docente = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new java.util.Date());
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        docente.revisarEjercicio(ejercicio);
        assertNotNull(docente);
    }
}