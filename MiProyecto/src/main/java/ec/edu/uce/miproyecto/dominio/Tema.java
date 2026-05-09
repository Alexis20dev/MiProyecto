package ec.edu.uce.miproyecto.dominio;
import java.util.ArrayList;
import java.util.List;

public class Tema {
    private String nombre;
    private String descripcion;
    private List<Concepto> conceptos;
    public Tema(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        conceptos = new ArrayList<>();
    }
    public void agregarConcepto(Concepto concepto) {
        conceptos.add(concepto);
    }
    public List<Concepto> mostrarConceptos() {
        return conceptos;
    }
    public String getNombre() {
        return nombre;
    }
}

