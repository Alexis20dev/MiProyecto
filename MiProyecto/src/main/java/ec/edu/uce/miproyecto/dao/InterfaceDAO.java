package ec.edu.uce.miproyecto.dao;

import java.util.List;

public interface InterfaceDAO {
        public boolean nuevo(Object objeto);
        public boolean editar(int pos, Object objeto);
        public Object buscar(String credencial);        public List listar();
        public boolean existe(Object objeto);
        public boolean eliminar(int pos);
}