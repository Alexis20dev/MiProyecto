package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PistaTest {

    @Test
    void getIdpista() {
        Pista pista = new Pista(5, "Revisa la derivada del exponente", 1);

        assertEquals(5, pista.getIdpista(), "El ID de la pista obtenido no coincide con el asignado.");
    }

    @Test
    void setIdpista() {
        Pista pista = new Pista();

        pista.setIdpista(12);

        assertEquals(12, pista.getIdpista(), "El ID de la pista no se modificó correctamente con el setter.");
    }

    @Test
    void getDescripcion() {
        Pista pista = new Pista(1, "Recuerda sumar la constante C al final", 2);

        assertEquals("Recuerda sumar la constante C al final", pista.getDescripcion(), "La descripción obtenida no coincide.");
    }

    @Test
    void setDescripcion() {
        Pista pista = new Pista();

        pista.setDescripcion("Usa la propiedad asociativa");

        assertEquals("Usa la propiedad asociativa", pista.getDescripcion(), "La descripción no se modificó correctamente.");
    }

    @Test
    void getOrden() {
        Pista pista = new Pista(1, "Pista inicial", 3);

        assertEquals(3, pista.getOrden(), "El orden obtenido no coincide con el asignado.");
    }

    @Test
    void setOrden() {
        Pista pista = new Pista();

        pista.setOrden(2);

        assertEquals(2, pista.getOrden(), "El orden no se modificó correctamente.");
    }

    @Test
    void testToString() {
        Pista pista = new Pista(1, "Factoriza el trinomio", 1);

        String resultadoToString = pista.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        assertTrue(resultadoToString.contains("Factoriza el trinomio"), "El toString() no incluye la descripción de la pista.");
        assertTrue(resultadoToString.contains("1"), "El toString() no incluye el orden o el ID asignado.");
    }
}