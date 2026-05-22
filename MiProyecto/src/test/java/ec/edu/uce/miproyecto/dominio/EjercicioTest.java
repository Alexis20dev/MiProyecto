package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EjercicioTest {

    @Test
    void mostrarEjercicio() {
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        ejercicio.mostrarEjercicio();
        assertNotNull(ejercicio);
    }

    @Test
    void getTema() {
        Tema tema = new Tema(1, "Integrales", "Cálculo Integral", null, null);
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", tema, null);
        assertEquals(tema, ejercicio.getTema());
    }

    @Test
    void setTema() {
        Ejercicio ejercicio = new Ejercicio();
        Tema tema = new Tema(1, "Integrales", "Cálculo Integral", null, null);
        ejercicio.setTema(tema);
        assertEquals(tema, ejercicio.getTema());
    }

    @Test
    void getPista() {
        Pista pista = new Pista(1, "Usa cambio de variable", 1);
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, pista);
        assertEquals(pista, ejercicio.getPista());
    }

    @Test
    void setPista() {
        Ejercicio ejercicio = new Ejercicio();
        Pista pista = new Pista(1, "Usa cambio de variable", 1);
        ejercicio.setPista(pista);
        assertEquals(pista, ejercicio.getPista());
    }

    @Test
    void getIdEjercicio() {
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        assertEquals(1, ejercicio.getIdEjercicio());
    }

    @Test
    void getEnunciado() {
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        assertEquals("Integral de 2x", ejercicio.getEnunciado());
    }

    @Test
    void getRespuesta() {
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        assertEquals("x^2", ejercicio.getRespuesta());
    }

    @Test
    void testToString() {
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", null, null);
        String esperado = "Ejercicio{idEjercicio=1, enunciado='Integral de 2x', respuesta='x^2', dificultad='Fácil'}";
        assertEquals(esperado, ejercicio.toString());
    }
}