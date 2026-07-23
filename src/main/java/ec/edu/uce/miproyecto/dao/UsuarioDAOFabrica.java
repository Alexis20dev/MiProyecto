package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Usuario;

public class UsuarioDAOFabrica {
    public InterfaceDAO<Usuario> crearUsuarioDAO(){
        return new UsuarioDAOArchivosImpl();    }
}