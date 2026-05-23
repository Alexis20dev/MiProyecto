package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EjercicioTest {

    @Test
    void getIdEjercicio() {
        Pista[] pistasSimuladas = new Pista[0];
        Ejercicio ejercicio = new Ejercicio(1, "Calcular la derivada de 3x", "3", "Fácil", pistasSimuladas);

        assertEquals(1, ejercicio.getIdEjercicio(), "El ID del ejercicio obtenido no coincide con el asignado.");
    }

    @Test
    void setIdEjercicio() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setIdEjercicio(42);

        assertEquals(42, ejercicio.getIdEjercicio(), "El ID no se modificó correctamente con el setter.");
    }

    @Test
    void getEnunciado() {
        Pista[] pistasSimuladas = new Pista[0];
        Ejercicio ejercicio = new Ejercicio(1, "Integral de e^x", "e^x", "Medio", pistasSimuladas);

        assertEquals("Integral de e^x", ejercicio.getEnunciado(), "El enunciado obtenido no coincide.");
    }

    @Test
    void setEnunciado() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setEnunciado("¿Cuánto es 2+2?");

        assertEquals("¿Cuánto es 2+2?", ejercicio.getEnunciado(), "El enunciado no se modificó correctamente.");
    }

    @Test
    void getRespuesta() {
        Pista[] pistasSimuladas = new Pista[0];
        Ejercicio ejercicio = new Ejercicio(1, "Derivada de x^2", "2x", "Fácil", pistasSimuladas);

        assertEquals("2x", ejercicio.getRespuesta(), "La respuesta obtenida no coincide.");
    }

    @Test
    void setRespuesta() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setRespuesta("4x^3");

        assertEquals("4x^3", ejercicio.getRespuesta(), "La respuesta no se modificó correctamente.");
    }

    @Test
    void getDificultad() {
        Pista[] pistasSimuladas = new Pista[0];
        Ejercicio ejercicio = new Ejercicio(1, "Límite complejo", "0", "Difícil", pistasSimuladas);

        assertEquals("Difícil", ejercicio.getDificultad(), "La dificultad obtenida no coincide.");
    }

    @Test
    void setDificultad() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDificultad("Medio");

        assertEquals("Medio", ejercicio.getDificultad(), "La dificultad no se modificó correctamente.");
    }

    @Test
    void getPistas() {
        Pista pistaPrueba = new Pista(1, "Recuerda la constante C", 1);
        Pista[] pistasSimuladas = new Pista[]{pistaPrueba};

        Ejercicio ejercicio = new Ejercicio(1, "Integral indefinida", "x+C", "Fácil", pistasSimuladas);

        assertNotNull(ejercicio.getPistas(), "El arreglo de pistas no debería ser nulo.");
        assertEquals(1, ejercicio.getPistas().length, "La cantidad de pistas obtenida no coincide.");
        assertEquals("Recuerda la constante C", ejercicio.getPistas()[0].getDescripcion(), "El contenido de la pista dentro del arreglo no es el correcto.");
    }

    @Test
    void setPistas() {
        Ejercicio ejercicio = new Ejercicio();
        Pista[] nuevasPistas = new Pista[3]; // Arreglo simulado con espacio para 3 pistas

        ejercicio.setPistas(nuevasPistas);

        assertNotNull(ejercicio.getPistas(), "El arreglo de pistas no se asignó correctamente.");
        assertEquals(3, ejercicio.getPistas().length, "El tamaño del arreglo modificado no coincide.");
    }

    @Test
    void testToString() {
        Pista[] pistasSimuladas = new Pista[0];
        Ejercicio ejercicio = new Ejercicio(5, "Derivada de 5x", "5", "Fácil", pistasSimuladas);

        String resultadoToString = ejercicio.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        assertTrue(resultadoToString.contains("Derivada de 5x"), "El toString() no incluye el enunciado del ejercicio.");
        assertTrue(resultadoToString.contains("Fácil"), "El toString() no incluye la dificultad.");
    }
}