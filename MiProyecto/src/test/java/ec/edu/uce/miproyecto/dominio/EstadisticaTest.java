package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticaTest {

    @Test
    void getIdEstadistica() {
        Estadistica estadistica = new Estadistica(1, 8, 2, 80.0, "Intermedio");
        assertEquals(1, estadistica.getIdEstadistica());
    }

    @Test
    void setIdEstadistica() {
        Estadistica estadistica = new Estadistica();
        estadistica.setIdEstadistica(10);
        assertEquals(10, estadistica.getIdEstadistica());
    }

    @Test
    void getAciertos() {
        Estadistica estadistica = new Estadistica(1, 8, 2, 80.0, "Intermedio");
        assertEquals(8, estadistica.getAciertos());
    }

    @Test
    void setAciertos() {
        Estadistica estadistica = new Estadistica();
        estadistica.setAciertos(15);
        assertEquals(15, estadistica.getAciertos());
    }

    @Test
    void getFallos() {
        Estadistica estadistica = new Estadistica(1, 8, 2, 80.0, "Intermedio");
        assertEquals(2, estadistica.getFallos());
    }

    @Test
    void setFallos() {
        Estadistica estadistica = new Estadistica();
        estadistica.setFallos(5);
        assertEquals(5, estadistica.getFallos());
    }

    @Test
    void getPorcentaje() {
        Estadistica estadistica = new Estadistica(1, 8, 2, 80.0, "Intermedio");
        assertEquals(80.0, estadistica.getPorcentaje());
    }

    @Test
    void setPorcentaje() {
        Estadistica estadistica = new Estadistica();
        estadistica.setPorcentaje(95.5);
        assertEquals(95.5, estadistica.getPorcentaje());
    }

    @Test
    void getNivel() {
        Estadistica estadistica = new Estadistica(1, 8, 2, 80.0, "Intermedio");
        assertEquals("Intermedio", estadistica.getNivel());
    }

    @Test
    void setNivel() {
        Estadistica estadistica = new Estadistica();
        estadistica.setNivel("Avanzado");
        assertEquals("Avanzado", estadistica.getNivel());
    }

    @Test
    void testToString() {
        Estadistica estadistica = new Estadistica(1, 8, 2, 80.0, "Intermedio");

        String esperado =
                "Estadistica{idEstadistica=1, aciertos=8, fallos=2, porcentaje=80.0, nivel='Intermedio'}";

        assertEquals(esperado, estadistica.toString());
    }
}