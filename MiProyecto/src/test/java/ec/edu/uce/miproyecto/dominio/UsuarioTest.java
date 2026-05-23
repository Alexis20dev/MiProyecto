package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void iniciarSesion() {
        Date fechaPrueba = new Date();
        // Usamos Estudiante para probar el comportamiento heredado de Usuario
        Usuario usuario = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());

        // 1. Caso de inicio de sesión exitoso por Correo
        assertTrue(usuario.iniciarSesion("jeremy@uce.edu.ec", "1234"), "El inicio de sesión debería ser exitoso con las credenciales correctas.");

        // 2. Caso de inicio de sesión fallido por contraseña incorrecta
        assertFalse(usuario.iniciarSesion("jeremy@uce.edu.ec", "clave_erronea"), "El inicio de sesión debería fallar con una contraseña incorrecta.");
    }

    @Test
    void cerrarSesion() {
        Date fechaPrueba = new Date();
        Usuario usuario = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());

        assertDoesNotThrow(() -> usuario.cerrarSesion(), "El método cerrarSesion() no debería lanzar ninguna excepción.");
    }

    @Test
    void verProgreso() {
        Date fechaPrueba = new Date();
        Progreso progresoSimulado = new Progreso();
        Estudiante estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", progresoSimulado);

        assertNotNull(estudiante.getProgreso(), "El progreso asociado al usuario no debería ser nulo.");
    }

    @Test
    void getIdUsuario() {
        Date fechaPrueba = new Date();
        Usuario usuario = new Estudiante(100, "Pedro", "pedro@uce.edu.ec", "pass", fechaPrueba, "Principiante", new Progreso());

        assertEquals(100, usuario.getIdUsuario(), "El ID de usuario obtenido no coincide con el asignado.");
    }

    @Test
    void setIdUsuario() {
        Usuario usuario = new Estudiante();
        usuario.setIdUsuario(500);

        assertEquals(500, usuario.getIdUsuario(), "El ID de usuario no se modificó correctamente con el setter.");
    }

    @Test
    void getNombre() {
        Date fechaPrueba = new Date();
        Usuario usuario = new Estudiante(1, "Ing. Lara", "lara@uce.edu.ec", "abcd", fechaPrueba, "Avanzado", new Progreso());

        assertEquals("Ing. Lara", usuario.getNombre(), "El nombre obtenido no coincide.");
    }

    @Test
    void setNombre() {
        Usuario usuario = new Estudiante();
        usuario.setNombre("Carlos");

        assertEquals("Carlos", usuario.getNombre(), "El nombre no se modificó correctamente con el setter.");
    }

    @Test
    void getEmail() {
        Date fechaPrueba = new Date();
        Usuario usuario = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());

        assertEquals("jeremy@uce.edu.ec", usuario.getEmail(), "El correo obtenido no coincide.");
    }

    @Test
    void setEmail() {
        Usuario usuario = new Estudiante();
        usuario.setEmail("nuevo@uce.edu.ec");

        assertEquals("nuevo@uce.edu.ec", usuario.getEmail(), "El correo no se modificó correctamente.");
    }

    @Test
    void getContrasena() {
        Date fechaPrueba = new Date();
        Usuario usuario = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());

        assertEquals("1234", usuario.getContrasena(), "La contraseña obtenida no coincide.");
    }

    @Test
    void setContrasena() {
        Usuario usuario = new Estudiante();
        usuario.setContrasena("secure99");

        assertEquals("secure99", usuario.getContrasena(), "La contraseña no se modificó correctamente.");
    }

    @Test
    void getFechaRegistro() {
        Date fechaPrueba = new Date();
        Usuario usuario = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());

        assertNotNull(usuario.getFechaRegistro(), "La fecha de registro no debería ser nula.");
        assertEquals(fechaPrueba, usuario.getFechaRegistro(), "La fecha de registro obtenida no coincide.");
    }

    @Test
    void setFechaRegistro() {
        Usuario usuario = new Estudiante();
        Date nuevaFecha = new Date();
        usuario.setFechaRegistro(nuevaFecha);

        assertEquals(nuevaFecha, usuario.getFechaRegistro(), "La fecha de registro no se modificó correctamente.");
    }

    @Test
    void testToString() {
        Date fechaPrueba = new Date();
        Usuario usuario = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());

        String resultadoToString = usuario.toString();

        assertNotNull(resultadoToString, "El método toString() devolvió null.");
        assertTrue(resultadoToString.contains("Jeremy"), "El toString() no incluye el nombre del usuario.");
        assertTrue(resultadoToString.contains("jeremy@uce.edu.ec"), "El toString() no incluye el email del usuario.");
    }
}