package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.*;

public interface TemaDAO {
    public boolean agregarConcepto(Tema tema, Concepto concepto);
    public boolean agregarEjercicio(Tema tema, Ejercicio ejercicio);
}
