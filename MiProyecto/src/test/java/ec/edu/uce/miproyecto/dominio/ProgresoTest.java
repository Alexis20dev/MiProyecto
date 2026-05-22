package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class ProgresoTest {

    @Test
    void actualizarProgreso() {
        Progreso progreso = new Progreso(1, "Pendiente", new Date(), 0, 30);
        progreso.actualizarProgreso(true);
        assertNotNull(progreso);
    }

    @Test
    void getIdProgreso() {
        Progreso progreso = new Progreso(1, "Aprobado", new Date(), 10, 30);
        assertEquals(1, progreso.getIdProgreso());
    }

    @Test
    void setIdProgreso() {
        Progreso progreso = new Progreso();
        progreso.setIdProgreso(10);
        assertEquals(10, progreso.getIdProgreso());
    }

    @Test
    void getEstado() {
        Progreso progreso = new Progreso(1, "Aprobado", new Date(), 10, 30);
        assertEquals("Aprobado", progreso.getEstado());
    }

    @Test
    void setEstado() {
        Progreso progreso = new Progreso();
        progreso.setEstado("Reprobado");
        assertEquals("Reprobado", progreso.getEstado());
    }

    @Test
    void getFecha() {
        Date fecha = new Date();
        Progreso progreso = new Progreso(1, "Aprobado", fecha, 10, 30);
        assertEquals(fecha, progreso.getFecha());
    }

    @Test
    void setFecha() {
        Progreso progreso = new Progreso();
        Date fecha = new Date();
        progreso.setFecha(fecha);
        assertEquals(fecha, progreso.getFecha());
    }

    @Test
    void getPuntaje() {
        Progreso progreso = new Progreso(1, "Aprobado", new Date(), 20, 30);
        assertEquals(20, progreso.getPuntaje());
    }

    @Test
    void setPuntaje() {
        Progreso progreso = new Progreso();
        progreso.setPuntaje(50);
        assertEquals(50, progreso.getPuntaje());
    }

    @Test
    void getTiempo() {
        Progreso progreso = new Progreso(1, "Aprobado", new Date(), 20, 45);
        assertEquals(45, progreso.getTiempo());
    }

    @Test
    void setTiempo() {
        Progreso progreso = new Progreso();
        progreso.setTiempo(60);
        assertEquals(60, progreso.getTiempo());
    }

    @Test
    void testToString() {
        Date fecha = new Date();
        Progreso progreso = new Progreso(1, "Aprobado", fecha, 20, 45);
        String esperado = "Progreso{idProgreso=1, estado='Aprobado', fecha=" + fecha + ", puntaje=20, tiempo=45}";
        assertEquals(esperado, progreso.toString());
    }
}
