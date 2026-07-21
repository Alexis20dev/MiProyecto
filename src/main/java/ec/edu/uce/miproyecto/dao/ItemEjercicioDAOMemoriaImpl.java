package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.ItemEjercicio;
import java.util.ArrayList;
import java.util.List;

public class ItemEjercicioDAOMemoriaImpl implements InterfaceDAO {

    private static List<ItemEjercicio> items = new ArrayList<>();

    @Override
    public boolean nuevo(Object objeto) throws DAOException {
        if (objeto == null) {
            throw new DAOException("El item de ejercicio no puede ser nulo.");
        }
        if (!(objeto instanceof ItemEjercicio)) {
            throw new DAOException("El objeto no corresponde a un ItemEjercicio válido.");
        }

        ItemEjercicio item = (ItemEjercicio) objeto;
        if (existe(item)) {
            throw new DAOException("El item de ejercicio ya existe en el sistema.");
        }

        try {
            return items.add(item);
        } catch (Exception e) {
            throw new DAOException("Error al guardar el item de ejercicio en memoria.", e);
        }
    }

    @Override
    public boolean editar(int pos, Object objeto) throws DAOException {
        if (objeto == null) {
            throw new DAOException("Los datos del item a editar no pueden ser nulos.");
        }
        if (!(objeto instanceof ItemEjercicio)) {
            throw new DAOException("El objeto no corresponde a un ItemEjercicio válido.");
        }
        if (pos < 0 || pos >= items.size()) {
            throw new DAOException("Posición fuera de rango para editar: " + pos);
        }

        try {
            items.set(pos, (ItemEjercicio) objeto);
            return true;
        } catch (Exception e) {
            throw new DAOException("Error al editar el item de ejercicio.", e);
        }
    }

    @Override
    public Object buscar(String credencial) throws DAOException {
        if (credencial == null || credencial.trim().isEmpty()) {
            throw new DAOException("La credencial de búsqueda no puede estar vacía.");
        }
        try {
            for (ItemEjercicio item : items) {
                if (item.getEjercicio() != null && item.getEjercicio().getEnunciado().equalsIgnoreCase(credencial)) {
                    return item;
                }
            }
            return null;
        } catch (Exception e) {
            throw new DAOException("Error durante la búsqueda del item de ejercicio.", e);
        }
    }

    @Override
    public List listar() {
        return items;
    }

    @Override
    public boolean existe(Object objeto) {
        if (objeto == null || !(objeto instanceof ItemEjercicio)) {
            return false;
        }
        return items.contains(objeto);
    }

    @Override
    public boolean eliminar(int pos) throws DAOException {
        if (pos < 0 || pos >= items.size()) {
            throw new DAOException("No se puede eliminar. Posición fuera de rango: " + pos);
        }
        try {
            items.remove(pos);
            return true;
        } catch (Exception e) {
            throw new DAOException("Error al eliminar el item de ejercicio.", e);
        }
    }
}