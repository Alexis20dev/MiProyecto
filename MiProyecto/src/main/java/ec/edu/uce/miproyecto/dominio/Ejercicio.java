package ec.edu.uce.miproyecto.dominio;

public class Ejercicio {

    private int idEjercicio;
    private String enuncido;
    private String respuesta;
    private String dificultad;


    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }


    public String getEnuncido() {
        return enuncido;
    }

    public void setEnuncido(String enuncido) {
        this.enuncido = enuncido;
    }


    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }


    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public ejercicio() {
    }

    public ejercicio(int idEjercicio, String enuncido, String respuesta, String dificultad) {
        this.idEjercicio = idEjercicio;
        this.enuncido = enuncido;
        this.respuesta = respuesta;
        this.dificultad = dificultad;
    }

    @Override
    public String toString() {
        return "ejercicio{" +
                "idEjercicio=" + idEjercicio +
                ", enuncido='" + enuncido + '\'' +
                ", respuesta='" + respuesta + '\'' +
                ", dificultad='" + dificultad + '\'' +
                '}';
    }
}

