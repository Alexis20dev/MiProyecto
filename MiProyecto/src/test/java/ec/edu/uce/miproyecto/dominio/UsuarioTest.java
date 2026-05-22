package ec.edu.uce.miproyecto.dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void iniciarSesion() {
        Date fecha = new Date();
        // Creamos un usuario de prueba usando el email
        Usuario usuario = new Usuario(1, "Paco", "paco@uce.edu.ec", "1234", fecha);

        // Caso 1: Credenciales correctas (Debe dar true)
        assertTrue(usuario.iniciarSesion("paco@uce.edu.ec", "1234"),
                "El inicio de sesión debería ser exitoso con las credenciales correctas.");

        // Caso 2: Contraseña incorrecta (Debe dar false)
        assertFalse(usuario.iniciarSesion("paco@uce.edu.ec", "claveErronea"),
                "El inicio de sesión debería fallar con contraseña incorrecta.");

        // Caso 3: Correo incorrecto (Debe dar false)
        assertFalse(usuario.iniciarSesion("incorrecto@uce.edu.ec", "1234"),
                "El inicio de sesión debería fallar con correo incorrecto.");
    }

    @Test
    void getIdUsuario() {
        Usuario usuario = new Usuario(10, "Jeremy", "jeremy@uce.edu.ec", "pass123", new Date());
        assertEquals(10, usuario.getIdUsuario(), "El ID retornado no coincide con el asignado.");
    }

    @Test
    void setIdUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(25);
        assertEquals(25, usuario.getIdUsuario(), "El ID no se actualizó correctamente.");
    }

    @Test
    void getNombre() {
        Usuario usuario = new Usuario(1, "Paco", "paco@uce.edu.ec", "1234", new Date());
        assertEquals("Paco", usuario.getNombre(), "El nombre retornado no es el esperado.");
    }

    @Test
    void setNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Luis");
        assertEquals("Luis", usuario.getNombre(), "El nombre no se modificó correctamente.");
    }

    @Test
    void getEmail() {
        Usuario usuario = new Usuario(1, "Paco", "paco@uce.edu.ec", "1234", new Date());
        assertEquals("paco@uce.edu.ec", usuario.getEmail(), "El email retornado no es el correcto.");
    }

    @Test
    void setEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("nuevo@uce.edu.ec");
        assertEquals("nuevo@uce.edu.ec", usuario.getEmail(), "El email no se actualizó correctamente.");
    }

    @Test
    void getContrasena() {
        Usuario usuario = new Usuario(1, "Paco", "paco@uce.edu.ec", "1234", new Date());
        assertEquals("1234", usuario.getContrasena(), "La contraseña retornada no coincide.");
    }

    @Test
    void setContrasena() {
        Usuario usuario = new Usuario();
        usuario.setContrasena("abcd");
        assertEquals("abcd", usuario.getContrasena(), "La contraseña no se cambió correctamente.");
    }

    @Test
    void getFechaRegistro() {
        Date fechaOriginal = new Date();
        Usuario usuario = new Usuario(1, "Paco", "paco@uce.edu.ec", "1234", fechaOriginal);
        assertEquals(fechaOriginal, usuario.getFechaRegistro(), "La fecha de registro no coincide.");
    }

    @Test
    void setFechaRegistro() {
        Usuario usuario = new Usuario();
        Date nuevaFecha = new Date();
        usuario.setFechaRegistro(nuevaFecha);
        assertEquals(nuevaFecha, usuario.getFechaRegistro(), "La fecha no se asignó correctamente.");
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario(1, "Paco", "paco@uce.edu.ec", "1234", new Date());
        assertNotNull(usuario.toString(), "El método toString() no debería retornar un valor nulo.");
        assertTrue(usuario.toString().contains("Paco"), "El toString() debería incluir el nombre del usuario.");
    }
}