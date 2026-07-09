package ec.edu.uce.miproyecto.dao;

public class UsuarioDAOFabrica {
    public InterfaceDAO crearUsuarioDAO(){
        return new UsuarioDAOMemoriaImpl();
    }
}