package ec.edu.uce.miproyecto.dao;

import java.util.List;

public interface InterfaceDAO {
        public boolean nuevo(Object objeto) throws DAOException;
        public boolean editar(int pos, Object objeto) throws DAOException;
        public Object buscar(String credencial) throws DAOException;
        public List listar();
        public boolean existe(Object objeto);
        public boolean eliminar(int pos) throws DAOException;
}