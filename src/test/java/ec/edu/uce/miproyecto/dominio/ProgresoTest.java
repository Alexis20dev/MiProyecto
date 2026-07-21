package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ProgresoTest {

    @Test
    void getIdProgreso() {
        Date fechaPrueba = new Date();
        ItemEjercicio[] itemsSimulados = new ItemEjercicio[0];
        Progreso progreso = new Progreso(1, "En Curso", fechaPrueba, 100, 45, itemsSimulados);

        assertEquals(1, progreso.getIdProgreso(), "El ID del progreso obtenido no coincide con el asignado.");
    }

    @Test
    void setIdProgreso() {
        Progreso progreso = new Progreso();
        progreso.setIdProgreso(15);

        assertEquals(15, progreso.getIdProgreso(), "El ID del progreso no se modificó correctamente con el setter.");
    }

    @Test
    void getEstado() {
        Date fechaPrueba = new Date();
        ItemEjercicio[] itemsSimulados = new ItemEjercicio[0];
        Progreso progreso = new Progreso(1, "Completado", fechaPrueba, 500, 120, itemsSimulados);

        assertEquals("Completado", progreso.getEstado(), "El estadoTema obtenido no coincide con el asignado.");
    }

    @Test
    void setEstado() {
        Progreso progreso = new Progreso();
        progreso.setEstado("Aprobado");

        assertEquals("Aprobado", progreso.getEstado(), "El estadoTema no se modificó correctamente con el setter.");
    }

    @Test
    void getFecha() {
        Date fechaPrueba = new Date();
        ItemEjercicio[] itemsSimulados = new ItemEjercicio[0];
        Progreso progreso = new Progreso(1, "Iniciado", fechaPrueba, 0, 0, itemsSimulados);

        assertNotNull(progreso.getFecha(), "La fecha de progreso no debería ser nula.");
        assertEquals(fechaPrueba, progreso.getFecha(), "La fecha obtenida no coincide con la fecha de asignación.");
    }

    @Test
    void setFecha() {
        Progreso progreso = new Progreso();
        Date nuevaFecha = new Date();
        progreso.setFecha(nuevaFecha);

        assertEquals(nuevaFecha, progreso.getFecha(), "La fecha no se actualizó correctamente con el setter.");
    }

    @Test
    void getPuntaje() {
        Date fechaPrueba = new Date();
        ItemEjercicio[] itemsSimulados = new ItemEjercicio[0];
        Progreso progreso = new Progreso(1, "En Curso", fechaPrueba, 250, 30, itemsSimulados);

        assertEquals(250, progreso.getPuntaje(), "El puntaje obtenido no coincide con el esperado.");
    }

    @Test
    void setPuntaje() {
        Progreso progreso = new Progreso();
        progreso.setPuntaje(750);

        assertEquals(750, progreso.getPuntaje(), "El puntaje no se modificó correctamente con el setter.");
    }

    @Test
    void getTiempo() {
        Date fechaPrueba = new Date();
        ItemEjercicio[] itemsSimulados = new ItemEjercicio[0];
        Progreso progreso = new Progreso(1, "En Curso", fechaPrueba, 150, 90, itemsSimulados);

        assertEquals(90, progreso.getTiempo(), "El tiempo en minutos obtenido no coincide.");
    }

    @Test
    void setTiempo() {
        Progreso progreso = new Progreso();
        progreso.setTiempo(60);

        assertEquals(60, progreso.getTiempo(), "El tiempo no se modificó correctamente con el setter.");
    }

    @Test
    void getItems() {
        Date fechaPrueba = new Date();
        ItemEjercicio itemPrueba = new ItemEjercicio(new Ejercicio(), "Nuevo");
        ItemEjercicio[] itemsSimulados = new ItemEjercicio[]{itemPrueba};

        Progreso progreso = new Progreso(1, "En Curso", fechaPrueba, 100, 15, itemsSimulados);

        assertNotNull(progreso.getItems(), "El arreglo de ítems de ejercicio no debería ser nulo.");
        assertEquals(1, progreso.getItems().length, "La cantidad de ítems dentro del progreso no coincide.");
    }

    @Test
    void setItems() {
        Progreso progreso = new Progreso();
        ItemEjercicio[] nuevosItems = new ItemEjercicio[5]; // Espacio simulado para 5 ítems

        progreso.setItems(nuevosItems);

        assertNotNull(progreso.getItems(), "El arreglo de ítems no se asignó correctamente.");
        assertEquals(5, progreso.getItems().length, "El tamaño del arreglo modificado no coincide.");
    }

    @Test
    void testToString() {
        Date fechaPrueba = new Date();
        ItemEjercicio[] itemsSimulados = new ItemEjercicio[0];
        Progreso progreso = new Progreso(1, "Finalizado", fechaPrueba, 1000, 180, itemsSimulados);

        String resultadoToString = progreso.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        assertTrue(resultadoToString.contains("Finalizado"), "El toString() no incluye el estadoTema del progreso.");
        assertTrue(resultadoToString.contains("1000"), "El toString() no incluye el puntaje acumulado.");
    }
}