package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {

    @Test
    void solicitarPista() {
        Pista pista = new Pista(1, "Usa sustitución");
        Ejercicio ejercicio = new Ejercicio(1, "Integral de x^2","x^3/3","Baja","Integral directa", "Suma 1 al exponente y divide sobre el nuevo exponente");

        Estudiante estudiante = new Estudiante(
                1,
                "Juan",
                "juan@uce.edu.ec",
                "1234",
                new Date(),
                null
        );

        assertEquals(pista, estudiante.solicitarPista(ejercicio));
    }

    @Test
    void verConcepto() {
        Concepto concepto = new Concepto(
                1,
                "Método de Sustitución",
                "Cambio de variable"
        );

        Estudiante estudiante = new Estudiante(
                1,
                "Juan",
                "juan@uce.edu.ec",
                "1234",
                new Date(),
                null
        );

        estudiante.verConcepto(concepto);

        assertNotNull(concepto);
    }

    @Test
    void resolverEjercicio() {
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 4x^3","x^4","Baja","Integrales directas","x^n=x^n/n");

        Estudiante estudiante = new Estudiante(
                1,
                "Juan",
                "juan@uce.edu.ec",
                "1234",
                new Date(),
                null
        );

        assertTrue(estudiante.resolverEjercicio(ejercicio, "x³/3"));
    }

    @Test
    void verProgreso() {
        Progreso progreso = new Progreso();

        Estudiante estudiante = new Estudiante(
                1,
                "Juan",
                "juan@uce.edu.ec",
                "1234",
                new Date(),
                progreso
        );

        assertEquals(progreso, estudiante.verProgreso());
    }

    @Test
    void getProgreso() {
        Progreso progreso = new Progreso();

        Estudiante estudiante = new Estudiante(
                1,
                "Juan",
                "juan@uce.edu.ec",
                "1234",
                new Date(),
                progreso
        );

        assertEquals(progreso, estudiante.getProgreso());
    }

    @Test
    void setProgreso() {
        Estudiante estudiante = new Estudiante(
                1,
                "Juan",
                "juan@uce.edu.ec",
                "1234",
                new Date(),
                null
        );

        Progreso progreso = new Progreso();

        estudiante.setProgreso(progreso);

        assertEquals(progreso, estudiante.getProgreso());
    }
}