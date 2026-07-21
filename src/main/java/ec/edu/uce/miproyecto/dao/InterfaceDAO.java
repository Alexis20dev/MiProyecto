package ec.edu.uce.miproyecto.dao;

import java.util.List;

public interface InterfaceDAO<T> {
        boolean nuevo(T objeto) throws DAOException;
        boolean editar(int pos, T objeto) throws DAOException;
        T buscar(String credencial) throws DAOException;
        List<T> listar();
        boolean existe(T objeto);
        boolean eliminar(int pos) throws DAOException;
}