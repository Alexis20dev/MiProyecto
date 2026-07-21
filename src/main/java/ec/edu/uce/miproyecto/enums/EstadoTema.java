package ec.edu.uce.miproyecto.enums;

public enum EstadoTema {
    ST("Sin Tema","ST"),
    Activo("Activo","Act"),
    En_Progreso("EnP","EnP"),
    Finalizado("Finalizado","Fin"),
    Inactivo("Inactivo","Inac");

    private final String abreviacion;
    private final String descripcion;

    private EstadoTema(String descripcion, String abreviacion) {
        this.descripcion = descripcion;
        this.abreviacion = abreviacion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public String getAbreviacion() {
        return abreviacion;
    }
}
