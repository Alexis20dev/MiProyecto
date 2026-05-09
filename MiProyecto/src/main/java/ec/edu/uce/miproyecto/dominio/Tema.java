package ec.edu.uce.miproyecto.dominio;
import java.util.Arrays;

public class Tema {
    private int idTema;
    private String tema;
    private String descripcion;
    private Concepto[] conceptos;

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Concepto[] getConceptos() {
        return conceptos;
    }

    public void setConceptos(Concepto[] conceptos) {
        this.conceptos = conceptos;
    }

    public Tema() {
    }

    public Tema(int idTema, String tema, String descripcion, Concepto[] conceptos) {
        this.idTema = idTema;
        this.tema = tema;
        this.descripcion = descripcion;
        this.conceptos = conceptos;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "idTema=" + idTema +
                ", tema='" + tema + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", conceptos=" + Arrays.toString(conceptos) +
                '}';
    }
}

