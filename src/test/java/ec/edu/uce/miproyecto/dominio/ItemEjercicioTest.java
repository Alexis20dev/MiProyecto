package ec.edu.uce.miproyecto.dominio;

import ec.edu.uce.miproyecto.enums.EstadoEjercicio;
import ec.edu.uce.miproyecto.enums.EstadoTema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemEjercicioTest {

    @Test
    void getEjercicio() {
        Ejercicio ejercicioBase = new Ejercicio("Integral de x", "x^2/2", "Fácil", new Pista[0]);

        ItemEjercicio item = new ItemEjercicio(ejercicioBase, EstadoEjercicio.Asignado);

        assertNotNull(item.getEjercicio(), "El ejercicio dentro del ítem no debería ser nulo.");
        assertEquals(ejercicioBase, item.getEjercicio(), "El ejercicio obtenido no coincide con el asignado.");
    }

    @Test
    void setEjercicio() {
        ItemEjercicio item = new ItemEjercicio();

        Ejercicio nuevoEjercicio = new Ejercicio("Derivada de ln(x)", "1/x", "Medio", new Pista[0]);
        item.setEjercicio(nuevoEjercicio);

        assertEquals(nuevoEjercicio, item.getEjercicio(), "El ejercicio no se modificó correctamente con el setter.");
    }

    @Test
    void getEstado() {
        Ejercicio ejercicioBase = new Ejercicio();
        ItemEjercicio item = new ItemEjercicio(ejercicioBase, EstadoEjercicio.En_Progreso);

        assertEquals(EstadoEjercicio.En_Progreso, item.getEstado(), "El estado de ejercicio obtenido no coincide con el asignado.");
    }

    @Test
    void setEstado() {
        ItemEjercicio item = new ItemEjercicio();

        item.setEstado(EstadoEjercicio.En_Progreso);

        assertEquals(EstadoEjercicio.En_Progreso, item.getEstado(), "El estado no se modificó correctamente con el setter.");
    }

    @Test
    void testToString() {
        Ejercicio ejercicioBase = new Ejercicio("Resolver 2+2", "4", "Fácil", new Pista[0]);
        ItemEjercicio item = new ItemEjercicio(ejercicioBase, EstadoEjercicio.Completado);

        String resultadoToString = item.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");

        assertTrue(resultadoToString.contains("Revisado"), "El toString() no incluye el estadoTema del ítem.");
        assertTrue(resultadoToString.contains("ItemEjercicio"), "El toString() no incluye el nombre de la clase.");
    }
}