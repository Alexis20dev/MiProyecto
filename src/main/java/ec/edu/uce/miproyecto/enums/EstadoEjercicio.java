package ec.edu.uce.miproyecto.enums;

public enum EstadoEjercicio {
    //Estado de Ejercicio
    Asignado("Asignado","A"),
    No_Asignado("No asignado","NA"),
    Completado("Completado","C"),
    En_Progreso("En progreso","En_P"),
    Pendiente("Pendiente","P");

    private final String abreviacion;
    private final String descripcion;

    private EstadoEjercicio(String descripcion, String abreviacion) {
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
