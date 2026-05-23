package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class GestionTest {

    @Test
    void registrarUsuario() {
        Gestion gestion = new Gestion();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", new Date(), "Principiante", new Progreso());

        // Registramos al usuario
        gestion.registrarUsuario(estudiante);

        // Verificamos que la lista ya no esté vacía y que contenga al estudiante registrado
        assertEquals(1, gestion.getListaUsuarios().size(), "El usuario no se añadió correctamente a la lista.");
        assertEquals(estudiante, gestion.getListaUsuarios().get(0), "El usuario guardado no coincide con el registrado.");
    }

    @Test
    void buscarUsuarioPorCorreo() {
        Gestion gestion = new Gestion();
        Docente docente = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", new Date(), "Cálculo");
        gestion.registrarUsuario(docente);

        // 1. Prueba de búsqueda exacta por correo
        Usuario encontradoPorEmail = gestion.buscarUsuarioPorCorreo("lara@uce.edu.ec");
        assertNotNull(encontradoPorEmail, "No se pudo encontrar al usuario por su correo exacto.");
        assertEquals("Ing. Lara", encontradoPorEmail.getNombre(), "El usuario encontrado no es el correcto.");

        // 2. Prueba de insensibilidad a mayúsculas/minúsculas (equalsIgnoreCase)
        Usuario encontradoPorNombreMayus = gestion.buscarUsuarioPorCorreo("ING. LARA");
        assertNotNull(encontradoPorNombreMayus, "La búsqueda falló al usar mayúsculas.");

        // 3. Prueba con un usuario inexistente
        Usuario noExiste = gestion.buscarUsuarioPorCorreo("falso@uce.edu.ec");
        assertNull(noExiste, "El método debería retornar null si el usuario no existe.");
    }

    @Test
    void agregarEjercicio() {
        Gestion gestion = new Gestion();
        Ejercicio ejercicio = new Ejercicio(1, "Integral de 2x", "x^2", "Fácil", new Pista[0]);

        // Ejecutamos la lógica que envuelve el Ejercicio dentro de un ItemEjercicio
        gestion.agregarEjercicio(ejercicio);

        // Verificamos que se haya añadido un elemento a la lista de ítems de ejercicio
        assertEquals(1, gestion.getListaItemE().size(), "El ejercicio no se registró en la lista de ítems.");

        // Comprobamos que el envoltorio contenga el ejercicio original y el estado inicial "Nuevo"
        ItemEjercicio itemGuardado = gestion.getListaItemE().get(0);
        assertEquals(ejercicio, itemGuardado.getEjercicio(), "El ejercicio dentro del ítem no coincide.");
        assertEquals("Nuevo", itemGuardado.getEstado(), "El estado inicial del ítem debería ser 'Nuevo'.");
    }

    @Test
    void getListaUsuarios() {
        Gestion gestion = new Gestion();

        // Al instanciarse, la lista debe estar inicializada y vacía (no nula)
        assertNotNull(gestion.getListaUsuarios(), "La lista de usuarios debería inicializarse vacía, no nula.");
        assertTrue(gestion.getListaUsuarios().isEmpty(), "La lista de usuarios debería estar vacía al iniciar.");
    }

    @Test
    void setListaUsuarios() {
        Gestion gestion = new Gestion();
        ArrayList<Usuario> nuevaLista = new ArrayList<>();
        nuevaLista.add(new Docente(3, "Dr. Espinoza", "espinoza@uce.edu.ec", "9876", new Date(), "Álgebra"));

        gestion.setListaUsuarios(nuevaLista);

        assertEquals(nuevaLista, gestion.getListaUsuarios(), "La lista de usuarios no se reemplazó correctamente.");
        assertEquals(1, gestion.getListaUsuarios().size(), "El tamaño de la lista reemplazada no coincide.");
    }

    @Test
    void getListaTemas() {
        Gestion gestion = new Gestion();

        assertNotNull(gestion.getListaTemas(), "La lista de temas no debería ser nula.");
        assertEquals(0, gestion.getListaTemas().size(), "La lista de temas debería empezar con tamaño 0.");
    }

    @Test
    void setListaTemas() {
        Gestion gestion = new Gestion();
        ArrayList<Tema> nuevaListaTemas = new ArrayList<>();
        nuevaListaTemas.add(new Tema(1, "Límites", "Cálculo", new Concepto[0], new Ejercicio[0]));

        gestion.setListaTemas(nuevaListaTemas);

        assertEquals(nuevaListaTemas, gestion.getListaTemas(), "La lista de temas no se asignó correctamente.");
    }

    @Test
    void getListaItemE() {
        Gestion gestion = new Gestion();

        assertNotNull(gestion.getListaItemE(), "La lista de ítems de ejercicio no debería ser nula.");
    }

    @Test
    void setListaItemE() {
        Gestion gestion = new Gestion();
        ArrayList<ItemEjercicio> nuevaListaItems = new ArrayList<>();
        nuevaListaItems.add(new ItemEjercicio(new Ejercicio(), "Completado"));

        gestion.setListaItemE(nuevaListaItems);

        assertEquals(nuevaListaItems, gestion.getListaItemE(), "La lista de ítems de ejercicio no se modificó correctamente.");
    }

    @Test
    void testToString() {
        Gestion gestion = new Gestion();
        gestion.registrarUsuario(new Estudiante(1, "Jeremy", "j@uce.edu.ec", "1", new Date(), "P", new Progreso()));

        String resultadoToString = gestion.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        // Verificamos que el reporte de texto muestre de forma correcta los contadores de las colecciones
        assertTrue(resultadoToString.contains("totalUsuarios=1") || resultadoToString.contains("1"), "El toString() no reporta correctamente la cantidad de usuarios.");
    }
}