package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConceptoTest {

    @Test
    void getIdConcepto() {
        Concepto concepto = new Concepto(10, "Límites", "Estudio de la tendencia de una función");
        assertEquals(10, concepto.getIdConcepto(), "El ID del concepto obtenido no coincide con el asignado.");
    }

    @Test
    void setIdConcepto() {
        Concepto concepto = new Concepto();
        concepto.setIdConcepto(25);
        assertEquals(25, concepto.getIdConcepto(), "El ID del concepto no se modificó correctamente con el setter.");
    }

    @Test
    void getNombre() {
        Concepto concepto = new Concepto(1, "Derivadas", "Razón de cambio instantánea");
        assertEquals("Derivadas", concepto.getNombre(), "El nombre obtenido no coincide con el asignado.");
    }

    @Test
    void setNombre() {
        Concepto concepto = new Concepto();
        concepto.setNombre("Integrales");
        assertEquals("Integrales", concepto.getNombre(), "El nombre no se modificó correctamente con el setter.");
    }

    @Test
    void getDescripcion() {
        Concepto concepto = new Concepto(1, "Sustitución", "Método de cambio de variable");

        assertEquals("Método de cambio de variable", concepto.getDescripcion(), "La descripción obtenida no coincide.");
    }

    @Test
    void setDescripcion() {
        Concepto concepto = new Concepto();

        concepto.setDescripcion("Área bajo la curva");

        assertEquals("Área bajo la curva", concepto.getDescripcion(), "La descripción no se modificó correctamente.");
    }

    @Test
    void testToString() {
        Concepto concepto = new Concepto(1, "Prueba", "Definición corta");

        String resultadoToString = concepto.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");

        assertTrue(resultadoToString.contains("Prueba"), "El toString() no incluye el nombre del concepto.");
        assertTrue(resultadoToString.contains("Definición corta"), "El toString() no incluye la descripción.");
    }
}