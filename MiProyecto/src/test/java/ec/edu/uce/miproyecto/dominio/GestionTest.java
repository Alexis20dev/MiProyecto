package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class GestionTest {

    @Test
    void registrarUsuario() {
        Gestion gestion = new Gestion();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", new Date(), "Principiante", new Progreso());

        gestion.registrarUsuario(estudiante);

        // 🚀 CORREGIDO: Comprobamos el contador real, no el tamaño del arreglo entero
        assertEquals(1, gestion.getNumUsuarios(), "El contador de usuarios no se incrementó.");
        assertEquals(estudiante, gestion.getListaUsuarios()[0], "El usuario guardado en la posición 0 no coincide.");
    }

    @Test
    void buscarUsuarioPorCorreo() {
        Gestion gestion = new Gestion();
        Docente docente = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new Date(), "Cálculo");
        gestion.registrarUsuario(docente);

        Usuario encontradoPorEmail = gestion.buscarUsuarioPorCorreo("lara@uce.edu.ec");
        assertNotNull(encontradoPorEmail, "No se pudo encontrar al usuario por su correo exacto.");
        assertEquals("Ing. Lara", encontradoPorEmail.getNombre(), "El usuario encontrado no es el correcto.");

        Usuario encontradoPorNombreMayus = gestion.buscarUsuarioPorCorreo("ING. LARA");
        assertNotNull(encontradoPorNombreMayus, "La búsqueda falló al usar mayúsculas.");

        Usuario noExiste = gestion.buscarUsuarioPorCorreo("falso@uce.edu.ec");
        assertNull(noExiste, "El método debería retornar null si el usuario no existe.");
    }

    @Test
    void agregarEjercicio() {
        Gestion gestion = new Gestion();
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", new Pista[0]);

        gestion.agregarEjercicio(ejercicio);

        // 🚀 CORREGIDO: Usamos getNumItemE() en lugar de .length
        assertEquals(1, gestion.getNumItemE(), "El ejercicio no se incrementó en el contador.");

        ItemEjercicio itemGuardado = gestion.getListaItemE()[0];
        assertNotNull(itemGuardado, "El ítem guardado en la posición 0 es nulo.");
        assertEquals(ejercicio, itemGuardado.getEjercicio(), "El ejercicio dentro del ítem no coincide.");
        assertEquals("Nuevo", itemGuardado.getEstado(), "El estado inicial debería ser 'Nuevo'.");
    }

    @Test
    void getListaUsuarios() {
        Gestion gestion = new Gestion();

        assertNotNull(gestion.getListaUsuarios(), "La lista de usuarios debería estar inicializada.");
        // 🚀 CORREGIDO: Al iniciar, el contador de usuarios registrados debe ser 0
        assertEquals(0, gestion.getNumUsuarios(), "Debería haber 0 usuarios registrados al inicializar.");
    }

    @Test
    void setListaUsuarios() {
        Gestion gestion = new Gestion();
        Usuario[] nuevaLista = new Usuario[100];
        nuevaLista[0] = new Estudiante(1, "Jeremy", "j@uce.edu.ec", "1", new Date(), "P", new Progreso());

        gestion.setListaUsuarios(nuevaLista);

        assertEquals(nuevaLista, gestion.getListaUsuarios(), "La lista no se reemplazó correctamente.");
        // 🚀 CORREGIDO: Nuestro setter recalcula el contador automáticamente
        assertEquals(1, gestion.getNumUsuarios(), "El contador de elementos no nulos no coincide.");
    }

    @Test
    void getListaTemas() {
        Gestion gestion = new Gestion();

        assertNotNull(gestion.getListaTemas(), "La lista de temas no debería ser nula.");
        assertEquals(0, gestion.getNumTemas(), "El número de temas registrados debería empezar en 0.");
    }

    @Test
    void setListaTemas() {
        Gestion gestion = new Gestion();
        Tema[] nuevaListaTemas = new Tema[100];
        nuevaListaTemas[0] = new Tema(1, "Integrales", "Cálculo", new Concepto[0], new Ejercicio[0]);

        gestion.setListaTemas(nuevaListaTemas);

        assertEquals(nuevaListaTemas, gestion.getListaTemas(), "La lista de temas no se asignó correctamente.");
        assertEquals(1, gestion.getNumTemas(), "El contador de temas no se actualizó.");
    }

    @Test
    void getListaItemE() {
        Gestion gestion = new Gestion();
        assertNotNull(gestion.getListaItemE(), "La lista de ítems de ejercicio no debería ser nula.");
        assertEquals(0, gestion.getNumItemE(), "El contador de ítems debería iniciar en 0.");
    }

    @Test
    void setListaItemE() {
        Gestion gestion = new Gestion();
        ItemEjercicio[] nuevaListaItems = new ItemEjercicio[100];
        nuevaListaItems[0] = new ItemEjercicio(new Ejercicio(1, "Ex", "x", "F", new Pista[0]), "Nuevo");

        gestion.setListaItemE(nuevaListaItems);

        assertEquals(nuevaListaItems, gestion.getListaItemE(), "La lista de ítems no se modificó correctamente.");
        assertEquals(1, gestion.getNumItemE(), "El contador de ítems no se actualizó.");
    }

    @Test
    void testToString() {
        Gestion gestion = new Gestion();
        gestion.registrarUsuario(new Estudiante(1, "Jeremy", "j@uce.edu.ec", "1", new Date(), "P", new Progreso()));

        String resultadoToString = gestion.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        // Verificamos que contenga el nombre de la variable de conteo modificada
        assertTrue(resultadoToString.contains("numUsuarios="), "El toString no contiene la estructura esperada.");
    }
}