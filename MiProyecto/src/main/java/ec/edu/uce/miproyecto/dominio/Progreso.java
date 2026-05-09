package ec.edu.uce.miproyecto.dominio;

public class Progreso {
    private int ejerciciosResueltos;
    private int ejerciciosCorrectos;
    public Progreso() {
        ejerciciosResueltos = 0;
        ejerciciosCorrectos = 0;
    }
    public void actualizarProgreso(boolean correcto) {
        ejerciciosResueltos++;
        if (correcto) {
            ejerciciosCorrectos++;
        }
    }
    public double calcularPromedio() {
        if (ejerciciosResueltos == 0) {
            return 0;
        }
        return (double) ejerciciosCorrectos
                / ejerciciosResueltos * 100;
    }
    public String mostrarEstadisticas() {

        return "Resueltos: "
                + ejerciciosResueltos
                + " | Correctos: "
                + ejerciciosCorrectos;
    }
}