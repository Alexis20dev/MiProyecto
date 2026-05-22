package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConceptoTest {

    @Test
    void mostrarExplicacion() {
        Concepto concepto = new Concepto(1, " Metodo de Sustitución", "Cambio de variable");
        concepto.mostrarExplicacion();
        assertNotNull(concepto);
    }
    @Test
    void getIdConcepto() {
        Concepto concepto = new Concepto(1, " Metodo de Sustitución", "Cambio de variable");
        assertEquals(1, concepto.getIdConcepto());
    }

    @Test
    void setIdConcepto() {
        Concepto concepto = new Concepto();
        concepto.setIdConcepto(10);
        assertEquals(10, concepto.getIdConcepto());
    }

    @Test
    void getNombre() {
        Concepto concepto = new Concepto(1, "Metodo de Sustitución", "Cambio de variable");
        assertEquals("Sustitución", concepto.getNombre());
    }

    @Test
    void setNombre() {
        Concepto concepto = new Concepto();
        concepto.setNombre("Por Partes");
        assertEquals("Por Partes", concepto.getNombre());
    }

    @Test
    void getDescripcion() {
        Concepto concepto = new Concepto(1, "Metodo de Sustitución", "Cambio de variable");
        assertEquals("Cambio de variable", concepto.getDescripcion());
    }

    @Test
    void setDescripcion() {
        Concepto concepto = new Concepto();
        concepto.setDescripcion("Método de integración");
        assertEquals("Método de integración", concepto.getDescripcion());
    }

    @Test
    void testToString() {
        Concepto concepto = new Concepto(1, "Sustitución", "Cambio de variable");
        String esperado = "Concepto{idConcepto=1, nombre='Metodo de Sustitución', descripcion='Cambio de variable'}";
        assertEquals(esperado, concepto.toString());
    }
}