package ec.edu.uce.miproyecto.dominio;

public enum Genero {
    F("F", "Femenino"),
    M("M", "Masculino");
    private final String descripcion;
    private final String abreviacion;

    private Genero(String descripcion, String abreviacion) {
        this.descripcion = descripcion;
        this.abreviacion = abreviacion;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getAbreviacion(){
        return abreviacion;
    }
}
