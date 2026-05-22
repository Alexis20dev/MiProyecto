package ec.edu.uce.miproyecto.dominio;

public class Ejercicio {
    private int idEjercicio;
    private String enunciado;
    private String respuesta;
    private String dificultad;
    private String tema;
    private String pista;

    public Ejercicio() {
        this.idEjercicio = 0;
        this.enunciado = "sin enunciado";
        this.respuesta = "sin respuesta";
        this.dificultad = "Baja/Media/Alta";
        this.tema = "sin tema";
        this.pista = "sin pista";
    }

    public Ejercicio(int idEjercicio, String enunciado, String respuesta, String dificultad, String tema, String pista) {
        this.idEjercicio = idEjercicio;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
        this.dificultad = dificultad;
        this.tema = tema;
        this.pista = pista;
    }

    // --- EL MÉTODO QUE FALTA ---
    public void mostrarEjercicio() {
        System.out.println("\n--- EJERCICIO #" + idEjercicio + " ---");
        System.out.println("Dificultad: " + dificultad);
        System.out.println("Enunciado: " + enunciado);
        System.out.println("---------------------------");
    }

    // Getters y Setters
    public Tema getTema() { return tema; }
    public void setTema(Tema tema) { this.tema = tema; }

    public Pista getPista() { return pista; }
    public void setPista(Pista pista) { this.pista = pista; }

    public int getIdEjercicio() { return idEjercicio; }
    public String getEnunciado() { return enunciado; }
    public String getRespuesta() { return respuesta; }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "id=" + idEjercicio +
                ", enunciado='" + enunciado + '\'' +
                ", tema=" + (tema != null ? getTema() : "N/A") +
                '}';
    }
}

