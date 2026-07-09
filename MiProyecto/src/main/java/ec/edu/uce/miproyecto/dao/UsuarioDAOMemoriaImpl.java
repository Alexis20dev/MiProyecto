package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Usuario;
import ec.edu.uce.miproyecto.dominio.Gestion;
import java.util.List;

public class UsuarioDAOMemoriaImpl implements InterfaceDAO{

    private static List<Usuario> usuarito = Gestion.getUsuarios();

    @Override
    public boolean nuevo(Object objeto) {
        if (objeto == null || !(objeto instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) objeto;
        if (existe(usuario)) {
            System.out.println("El email ingresado ya se encuentra registrado.");
            return false;
        }
        usuarito.add(usuario);
        return true;
    }

    @Override
    public boolean editar(int pos, Object objeto) {
        if (objeto == null || !(objeto instanceof Usuario) || pos < 0 || pos >= usuarito.size()) {
            return false;
        }
        usuarito.set(pos, (Usuario) objeto);
        return true;
    }

    @Override
    public Object buscar(String credencial) {
        if (credencial == null) return null;
        for (Usuario u : usuarito) {
            if (u.getEmail().equalsIgnoreCase(credencial) || u.getNombre().equalsIgnoreCase(credencial)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List listar() {
        return usuarito;
    }

    @Override
    public boolean existe(Object objeto) {
        if (objeto == null || !(objeto instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) objeto;
        for (Usuario u : usuarito) {
            if (u.equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int pos) {
        if (pos >= 0 && pos < usuarito.size()) {
            usuarito.remove(pos);
            return true;
        }
        return false;
    }


}