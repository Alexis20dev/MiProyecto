package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemaTest {

    @Test
    void getIdTema() {
        Concepto[] conceptosSimulados = new Concepto[0];
        Ejercicio[] ejerciciosSimulados = new Ejercicio[0];
        Tema tema = new Tema(7, "Integrales por Sustitución", "Métodos avanzadas de integración", conceptosSimulados, ejerciciosSimulados);

        assertEquals(7, tema.getIdTema(), "El ID del tema obtenido no coincide con el asignado.");
    }

    @Test
    void setIdTema() {
        Tema tema = new Tema();
        tema.setIdTema(14);

        assertEquals(14, tema.getIdTema(), "El ID del tema no se modificó correctamente con el setter.");
    }

    @Test
    void getNombre() {
        Concepto[] conceptosSimulados = new Concepto[0];
        Ejercicio[] ejerciciosSimulados = new Ejercicio[0];
        Tema tema = new Tema(1, "Límites", "Introducción al cálculo", conceptosSimulados, ejerciciosSimulados);

        assertEquals("Límites", tema.getNombre(), "El nombre del tema obtenido no coincide.");
    }

    @Test
    void setNombre() {
        Tema tema = new Tema();
        tema.setNombre("Derivadas");

        assertEquals("Derivadas", tema.getNombre(), "El nombre no se modificó correctamente con el setter.");
    }

    @Test
    void getDescripcion() {
        Concepto[] conceptosSimulados = new Concepto[0];
        Ejercicio[] ejerciciosSimulados = new Ejercicio[0];
        Tema tema = new Tema(1, "Álgebra Lineal", "Matrices y vectores", conceptosSimulados, ejerciciosSimulados);

        assertEquals("Matrices y vectores", tema.getDescripcion(), "La descripción obtenida no coincide.");
    }

    @Test
    void setDescripcion() {
        Tema tema = new Tema();
        tema.setDescripcion("Estudio del área bajo la curva");

        assertEquals("Estudio del área bajo la curva", tema.getDescripcion(), "La descripción no se modificó correctamente.");
    }

    @Test
    void getConceptos() {
        Concepto conceptoPrueba = new Concepto(1, "Definición de Integral", "Operación inversa a la derivación");
        Concepto[] conceptosSimulados = new Concepto[]{conceptoPrueba};
        Ejercicio[] ejerciciosSimulados = new Ejercicio[0];

        Tema tema = new Tema(1, "Cálculo", "Generalidades", conceptosSimulados, ejerciciosSimulados);

        assertNotNull(tema.getConceptos(), "El arreglo de conceptos no debería ser nulo.");
        assertEquals(1, tema.getConceptos().length, "La cantidad de conceptos en el arreglo no coincide.");
        assertEquals("Definición de Integral", tema.getConceptos()[0].getNombre(), "El concepto dentro del arreglo no es el correcto.");
    }

    @Test
    void setConceptos() {
        Tema tema = new Tema();
        Concepto[] nuevosConceptos = new Concepto[2]; // Simulación de arreglo con espacio para 2 objetos

        tema.setConceptos(nuevosConceptos);

        assertNotNull(tema.getConceptos(), "El arreglo de conceptos no se asignó correctamente.");
        assertEquals(2, tema.getConceptos().length, "El tamaño del arreglo modificado no coincide.");
    }

    @Test
    void getEjercicios() {
        Concepto[] conceptosSimulados = new Concepto[0];
        Ejercicio ejercicioPrueba = new Ejercicio(1, "Integral de x^2", "x^3/3", "Fácil", new Pista[0]);
        Ejercicio[] ejerciciosSimulados = new Ejercicio[]{ejercicioPrueba};

        Tema tema = new Tema(1, "Cálculo Integral", "Prácticas", conceptosSimulados, ejerciciosSimulados);

        assertNotNull(tema.getEjercicios(), "El arreglo de ejercicios no debería ser nulo.");
        assertEquals(1, tema.getEjercicios().length, "La cantidad de ejercicios en el arreglo no coincide.");
        assertEquals("Integral de x^2", tema.getEjercicios()[0].getEnunciado(), "El ejercicio dentro del arreglo no coincide.");
    }

    @Test
    void setEjercicios() {
        Tema tema = new Tema();
        Ejercicio[] nuevosEjercicios = new Ejercicio[4]; // Simulación de arreglo con espacio para 4 objetos

        tema.setEjercicios(nuevosEjercicios);

        assertNotNull(tema.getEjercicios(), "El arreglo de ejercicios no se asignó correctamente.");
        assertEquals(4, tema.getEjercicios().length, "El tamaño del arreglo de ejercicios modificado no coincide.");
    }

    @Test
    void testToString() {
        Concepto[] conceptosSimulados = new Concepto[0];
        Ejercicio[] ejerciciosSimulados = new Ejercicio[0];
        Tema tema = new Tema(5, "Geometría Analítica", "Secciones cónicas", conceptosSimulados, ejerciciosSimulados);

        String resultadoToString = tema.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        assertTrue(resultadoToString.contains("Geometría Analítica"), "El toString() no incluye el nombre del tema.");
        assertTrue(resultadoToString.contains("Secciones cónicas"), "El toString() no incluye la descripción.");
    }
}