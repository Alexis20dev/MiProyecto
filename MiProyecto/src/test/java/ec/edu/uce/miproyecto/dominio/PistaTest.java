package ec.edu.uce.miproyecto.dominio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PistaTest {

    @Test
    void mostrarPista() {
        Pista pista = new Pista(1, "Usa el método de sustitución", 1);
        pista.mostrarPista();
        assertNotNull(pista); // Comprueba que el objeto exista, si no existe la prueba test falla
    }

    @Test
    void getIdpista() {
        Pista pista = new Pista(1, "Usa el método de sustitución", 1);
        assertEquals(1, pista.getIdpista());
    }

    @Test
    void setIdpista() {
        Pista pista = new Pista();
        pista.setIdpista(10);
        assertEquals(10, pista.getIdpista());
    }

    @Test
    void getDescripcion() {
        Pista pista = new Pista(1, "Usa el método de sustitución", 1);
        assertEquals("Usa el método de sustitución", pista.getDescripcion());
    }

    @Test
    void setDescripcion() {
        Pista pista = new Pista();
        pista.setDescripcion("Aplica integración por partes");
        assertEquals("Aplica integración por partes", pista.getDescripcion());
    }

    @Test
    void getOrden() {
        Pista pista = new Pista(1, "Usa el método de sustitución", 2);
        assertEquals(2, pista.getOrden());
    }

    @Test
    void setOrden() {
        Pista pista = new Pista();
        pista.setOrden(5);
        assertEquals(5, pista.getOrden());
    }

    @Test
    void testToString() {
        Pista pista = new Pista(1, "Usa el método de sustitución", 1);
        String esperado = "Pista{idpista=1, descripcion='Usa el método de sustitución', orden=1}";
        assertEquals(esperado, pista.toString());
    }
}
