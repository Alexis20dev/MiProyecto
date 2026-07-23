package ec.edu.uce.miproyecto.enums;

public enum Genero {
    S("Sin genero","SN"),
    F("Femenino", "F"),
    M("Masculino", "M");
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

    @Override
    public String toString() {return  descripcion;}
}
