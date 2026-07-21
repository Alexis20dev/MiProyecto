package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Usuario;
import ec.edu.uce.miproyecto.dominio.Docente;
import ec.edu.uce.miproyecto.dominio.Estudiante;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOArchivosImpl implements InterfaceDAO {

    private final String ARCHIVO = "usuarios.txt";

    private void guardarEnArchivo(List<Usuario> lista) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : lista) {
                String tipo = (u instanceof Docente) ? "Docente" : "Estudiante";
                writer.write(tipo + "," + u.getNombre() + "," + u.getEmail() + "," + u.getContrasena());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DAOException("Error al escribir en el archivo de texto", e);
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return lista;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    String tipo = datos[0];
                    String nombre = datos[1];
                    String email = datos[2];
                    String contra = datos[3];

                    Usuario u;
                    if ("Docente".equalsIgnoreCase(tipo)) {
                        u = new Docente();
                    } else {
                        u = new Estudiante();
                    }
                    u.setNombre(nombre);
                    u.setEmail(email);
                    u.setContrasena(contra);
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean nuevo(Object objeto) throws DAOException {
        if (!(objeto instanceof Usuario)) {
            throw new DAOException("El objeto ingresado no es un usuario válido.");
        }

        Usuario nuevoUsuario = (Usuario) objeto;
        List<Usuario> usuarios = listar();

        for (Usuario usuarioRegistrado : usuarios) {
            if (usuarioRegistrado.getEmail().equalsIgnoreCase(
                    nuevoUsuario.getEmail())) {

                throw new DAOException(
                        "El correo " + nuevoUsuario.getEmail()
                                + " ya está registrado."
                );
            }
        }
        usuarios.add(nuevoUsuario);
        guardarEnArchivo(usuarios);

        return true;
    }

    @Override
    public boolean editar(int pos, Object objeto) throws DAOException {
        List<Usuario> lista = listar();
        if (pos >= 0 && pos < lista.size()) {
            lista.set(pos, (Usuario) objeto);
            guardarEnArchivo(lista);
            return true;
        }
        throw new DAOException("Posición fuera de rango");
    }

    @Override
    public Object buscar(String credencial) throws DAOException {
        List<Usuario> lista = listar(); // Lee con Reader
        for (Usuario u : lista) {
            if (u.getEmail().equalsIgnoreCase(credencial) || u.getNombre().equalsIgnoreCase(credencial)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean existe(Object objeto) {
        List<Usuario> lista = listar();
        Usuario usuario = (Usuario) objeto;
        for (Usuario u : lista) {
            if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int pos) throws DAOException {
        List<Usuario> lista = listar();
        if (pos >= 0 && pos < lista.size()) {
            lista.remove(pos);
            guardarEnArchivo(lista);
            return true;
        }
        throw new DAOException("Posición fuera de rango");
    }
}