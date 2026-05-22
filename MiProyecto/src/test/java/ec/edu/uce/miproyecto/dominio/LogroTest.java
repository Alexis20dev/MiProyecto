package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogroTest {

    @Test
    void getIdLogro() {
        // Creamos un logro de prueba con datos ficticios para el constructor
        // Ajusta el orden de los parámetros según tu constructor real si es necesario
        Logro logro = new Logro(1, "Maestro de Integrales", "Resuelve 10 ejercicios seguidos", "cantidadEjercicios >= 10");
        assertEquals(1, logro.getIdLogro(), "El ID del logro retornado no coincide con el asignado.");
    }

    @Test
    void setIdLogro() {
        Logro logro = new Logro();
        logro.setIdLogro(5);
        assertEquals(5, logro.getIdLogro(), "El ID del logro no se actualizó correctamente con el setter.");
    }

    @Test
    void getNombre() {
        Logro logro = new Logro(2, "Primer Paso", "Completa tu primer perfil", "perfilCreado == true");
        assertEquals("Primer Paso", logro.getNombre(), "El nombre del logro retornado no es el correcto.");
    }

    @Test
    void setNombre() {
        Logro logro = new Logro();
        logro.setNombre("Cazador de Errores");
        assertEquals("Cazador de Errores", logro.getNombre(), "El nombre del logro no se modificó correctamente.");
    }

    @Test
    void getDescripcion() {
        Logro logro = new Logro(3, "Veloz", "Resuelve un ejercicio en menos de 1 minuto", "tiempo < 60");
        assertEquals("Resuelve un ejercicio en menos de 1 minuto", logro.getDescripcion(), "La descripción retornada no coincide.");
    }

    @Test
    void setDescripcion() {
        Logro logro = new Logro();
        logro.setDescripcion("Completa el nivel básico");
        assertEquals("Completa el nivel básico", logro.getDescripcion(), "La descripción no se actualizó correctamente.");
    }

    @Test
    void getCondicion() {
        Logro logro = new Logro(4, "Constancia", "Ingresa 5 días seguidos", "rachas == 5");
        assertEquals("rachas == 5", logro.getCondicion(), "La condición del logro retornada no coincide.");
    }

    @Test
    void setCondicion() {
        Logro logro = new Logro();
        logro.setCondicion("puntaje >= 100");
        assertEquals("puntaje >= 100", logro.getCondicion(), "La condición no se cambió correctamente.");
    }

    @Test
    void testToString() {
        Logro logro = new Logro(1, "Perfecto", "Cero errores", "errores == 0");
        assertNotNull(logro.toString(), "El método toString() de Logro no debería retornar null.");
        assertTrue(logro.toString().contains("Perfecto"), "El toString() debería contener al menos el nombre del logro.");
    }
}