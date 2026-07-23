package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Docente;
import ec.edu.uce.miproyecto.dominio.Estudiante;
import ec.edu.uce.miproyecto.dominio.Usuario;
import ec.edu.uce.miproyecto.enums.Genero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOArchivosImpl implements InterfaceDAO<Usuario> {

    private static final String ARCHIVO = "usuarios.txt";

    private void guardarEnArchivo(List<Usuario> lista) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : lista) {
                String tipo = (u instanceof Docente) ? "Docente" : "Estudiante";
                Genero genero = u.getGenero() != null ? u.getGenero() : Genero.S;

                writer.write(tipo + "," + u.getNombre() + "," + u.getEmail() + ","
                        + u.getContrasena() + "," + genero.name());
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
                String[] datos = linea.split(",", -1);
                if (datos.length >= 4) {
                    String tipo = datos[0].trim();
                    String nombre = datos[1].trim();
                    String email = datos[2].trim();
                    String contra = datos[3];
                    Genero genero = datos.length >= 5 ? convertirGenero(datos[4]) : Genero.S;

                    Usuario u = "Docente".equalsIgnoreCase(tipo) ? new Docente() : new Estudiante();
                    u.setNombre(nombre);
                    u.setEmail(email);
                    u.setContrasena(contra);
                    u.setGenero(genero);
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    private Genero convertirGenero(String valor) {
        if (valor == null || valor.trim().isEmpty()) return Genero.S;

        String texto = valor.trim();
        for (Genero genero : Genero.values()) {
            if (genero.name().equalsIgnoreCase(texto)
                    || genero.getAbreviacion().equalsIgnoreCase(texto)
                    || genero.getDescripcion().equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        return Genero.S;
    }

    @Override
    public boolean nuevo(Usuario nuevoUsuario) throws DAOException {
        if (nuevoUsuario == null) {
            throw new DAOException("El usuario ingresado no es válido.");
        }

        List<Usuario> usuarios = listar();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(nuevoUsuario.getEmail())) {
                throw new DAOException("El correo " + nuevoUsuario.getEmail() + " ya está registrado.");
            }
            if (usuario.getNombre().equalsIgnoreCase(nuevoUsuario.getNombre())) {
                throw new DAOException("El nombre de usuario '" + nuevoUsuario.getNombre()
                        + "' ya está registrado. Use otro nombre.");
            }
        }

        usuarios.add(nuevoUsuario);
        guardarEnArchivo(usuarios);
        return true;
    }

    @Override
    public boolean editar(int pos, Usuario usuario) throws DAOException {
        List<Usuario> lista = listar();
        if (usuario == null) {
            throw new DAOException("El usuario no puede ser nulo.");
        }
        if (pos < 0 || pos >= lista.size()) {
            throw new DAOException("Posición fuera de rango");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (i == pos) continue;
            Usuario existente = lista.get(i);
            if (existente.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new DAOException("El correo ya pertenece a otro usuario.");
            }
            if (existente.getNombre().equalsIgnoreCase(usuario.getNombre())) {
                throw new DAOException("El nombre de usuario ya pertenece a otra cuenta.");
            }
        }

        lista.set(pos, usuario);
        guardarEnArchivo(lista);
        return true;
    }

    @Override
    public Usuario buscar(String credencial) throws DAOException {
        if (credencial == null || credencial.trim().isEmpty()) {
            throw new DAOException("Debe ingresar el nombre de usuario o el correo.");
        }

        String buscado = credencial.trim();
        List<Usuario> lista = listar();

        // Primero busca correo, porque siempre debe ser único.
        for (Usuario usuario : lista) {
            if (usuario.getEmail().equalsIgnoreCase(buscado)) {
                return usuario;
            }
        }

        // Si no era correo, busca por nombre de usuario.
        for (Usuario usuario : lista) {
            if (usuario.getNombre().equalsIgnoreCase(buscado)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public boolean existe(Usuario usuario) {
        if (usuario == null) return false;

        for (Usuario existente : listar()) {
            if (existente.getEmail().equalsIgnoreCase(usuario.getEmail())
                    || existente.getNombre().equalsIgnoreCase(usuario.getNombre())) {
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
