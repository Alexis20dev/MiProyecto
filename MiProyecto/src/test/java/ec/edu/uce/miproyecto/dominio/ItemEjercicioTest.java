package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemEjercicioTest {

    @Test
    void getEjercicio() {
        Ejercicio ejercicioBase = new Ejercicio(1, "Integral de x", "x^2/2", "Fácil", new Pista[0]);

        ItemEjercicio item = new ItemEjercicio(ejercicioBase, "Nuevo");

        assertNotNull(item.getEjercicio(), "El ejercicio dentro del ítem no debería ser nulo.");
        assertEquals(ejercicioBase, item.getEjercicio(), "El ejercicio obtenido no coincide con el asignado.");
    }

    @Test
    void setEjercicio() {
        ItemEjercicio item = new ItemEjercicio();

        Ejercicio nuevoEjercicio = new Ejercicio(2, "Derivada de ln(x)", "1/x", "Medio", new Pista[0]);
        item.setEjercicio(nuevoEjercicio);

        assertEquals(nuevoEjercicio, item.getEjercicio(), "El ejercicio no se modificó correctamente con el setter.");
    }

    @Test
    void getEstado() {
        Ejercicio ejercicioBase = new Ejercicio();
        ItemEjercicio item = new ItemEjercicio(ejercicioBase, "En Progreso");

        assertEquals("En Progreso", item.getEstado(), "El estado obtenido no coincide con el asignado.");
    }

    @Test
    void setEstado() {
        ItemEjercicio item = new ItemEjercicio();

        item.setEstado("Completado");

        assertEquals("Completado", item.getEstado(), "El estado no se modificó correctamente con el setter.");
    }

    @Test
    void testToString() {
        Ejercicio ejercicioBase = new Ejercicio(3, "Resolver 2+2", "4", "Fácil", new Pista[0]);
        ItemEjercicio item = new ItemEjercicio(ejercicioBase, "Revisado");

        String resultadoToString = item.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");

        assertTrue(resultadoToString.contains("Revisado"), "El toString() no incluye el estado del ítem.");
        assertTrue(resultadoToString.contains("ItemEjercicio"), "El toString() no incluye el nombre de la clase.");
    }
}