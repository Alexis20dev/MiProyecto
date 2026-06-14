package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void iniciarSesion() {
        Date fechaPrueba = new Date();
        // Es correcto instanciar como Estudiante ya que Usuario ahora es abstract y no permite "new Usuario()"
        Usuario usuario = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());

        // Caso exitoso
        assertTrue(usuario.iniciarSesion("jeremy@uce.edu.ec", "1234"), "El inicio de sesión debería ser exitoso con las credenciales correctas.");

        // Caso fallido
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
        Usuario estudiante = new Estudiante(1, "Jeremy", "jeremy@uce.edu.ec", "1234", fechaPrueba, "Principiante", new Progreso());
        Usuario docente = new Docente(2, "Ing. Lara", "lara@uce.edu.ec", "abcd", fechaPrueba, "Sistemas");

        assertDoesNotThrow(() -> estudiante.verProgreso(), "El verProgreso de Estudiante no debe lanzar excepciones.");
        assertDoesNotThrow(() -> docente.verProgreso(), "El verProgreso de Docente no debe lanzar excepciones.");
    }

    @Test
    void testEquals() {
        Date fecha = new Date();
        Usuario usuario1 = new Estudiante(15, "Jeremy", "jeremy@uce.edu.ec", "1234", fecha, "Principiante", new Progreso());
        Usuario usuario2 = new Estudiante(15, "Jeremy Salazar", "salazar@uce.edu.ec", "9999", fecha, "Avanzado", new Progreso());

        Usuario usuario3 = new Estudiante(30, "Pedro", "pedro@uce.edu.ec", "pass", fecha, "Principiante", new Progreso());

        assertEquals(usuario1, usuario2, "Deberían ser iguales porque comparten el mismo idUsuario.");
        assertNotEquals(usuario1, usuario3, "No deberían ser iguales porque tienen diferentes idUsuario.");
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