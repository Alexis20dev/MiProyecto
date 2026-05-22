package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemaTest {

    @Test
    void getIdTema() {
        Tema tema = new Tema();
        tema.setIdTema(1);
        assertEquals(1, tema.getIdTema());
    }

    @Test
    void setIdTema() {
        Tema tema = new Tema();
        tema.setIdTema(10);
        assertEquals(10, tema.getIdTema());
    }

    @Test
    void getNombre() {
        Tema tema = new Tema();
        tema.setNombre("Integrales");
        assertEquals("Integrales", tema.getNombre());
    }

    @Test
    void setNombre() {
        Tema tema = new Tema();
        tema.setNombre("Derivadas");
        assertEquals("Derivadas", tema.getNombre());
    }

    @Test
    void getDescripcion() {
        Tema tema = new Tema();
        tema.setDescripcion("Tema de cálculo integral");
        assertEquals("Tema de cálculo integral", tema.getDescripcion());
    }

    @Test
    void setDescripcion() {
        Tema tema = new Tema();
        tema.setDescripcion("Tema de cálculo diferencial");
        assertEquals("Tema de cálculo diferencial", tema.getDescripcion());
    }

    @Test
    void getConceptos() {
        Concepto[] conceptos = {
                new Concepto(1, "Sustitución", "Cambio de variable")
        };
        Tema tema = new Tema(1, "Integrales", "Tema de cálculo", conceptos, null);
        assertEquals(1, tema.getConceptos().length);
    }

    @Test
    void setConceptos() {
        Tema tema = new Tema();
        Concepto[] conceptos = {
                new Concepto(1, "Por Partes", "Método de integración")
        };
        tema.setConceptos(conceptos);
        assertEquals(1, tema.getConceptos().length);
    }

    @Test
    void getEjercicios() {
        Ejercicio[] ejercicios = {
                new Ejercicio()
        };
        Tema tema = new Tema(1, "Integrales", "Tema de cálculo", null, ejercicios);
        assertEquals(1, tema.getEjercicios().length);
    }

    @Test
    void setEjercicios() {
        Tema tema = new Tema();

        Ejercicio[] ejercicios = {
                new Ejercicio()
        };
        tema.setEjercicios(ejercicios);
        assertEquals(1, tema.getEjercicios().length);
    }

    @Test
    void testToString() {
        Tema tema = new Tema(1, "Integrales", "Tema de cálculo", null, null);
        String resultado = tema.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("Integrales"));
        assertTrue(resultado.contains("Tema de cálculo"));
    }
}
