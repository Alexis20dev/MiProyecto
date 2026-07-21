package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Concepto;
import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dominio.Tema;

public interface TemaDAO extends InterfaceDAO<Tema> {
    boolean agregarConcepto(Tema tema, Concepto concepto) throws DAOException;
    boolean agregarEjercicio(Tema tema, Ejercicio ejercicio) throws DAOException;
}