package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dominio.Pista;

import java.util.List;

public interface EjercicioDAO {
    public void nuevo(Ejercicio ejercicio);
    public void editar(Ejercicio ejercicio);
    public void eliminar(Ejercicio ejercicio);
    public boolean existe(Ejercicio ejercicio);
//    public void agregarPista(Ejercicio ejercicio,Pista pista);
//    public void eliminarPista(Ejercicio ejercicio,Pista pista);
    public List<Ejercicio> listarEjercicios();
//    public List<Pista> listarPistas(Ejercicio ejercicio);
}
