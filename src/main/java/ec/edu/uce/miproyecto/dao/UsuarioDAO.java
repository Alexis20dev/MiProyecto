package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Usuario;
import java.util.List;

public interface UsuarioDAO {
    public boolean registrar(Usuario usuario);
    public boolean editar(Usuario usuario);
    public Usuario buscar(String credencial);
    public boolean existe(Usuario  usario);
    public List<Usuario> listarTodos();
    //IDAO
}