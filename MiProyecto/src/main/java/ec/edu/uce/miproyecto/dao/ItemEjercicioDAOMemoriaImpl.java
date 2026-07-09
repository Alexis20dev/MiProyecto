package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.ItemEjercicio;
import java.util.ArrayList;
import java.util.List;

public class ItemEjercicioDAOMemoriaImpl implements InterfaceDAO {

    private static List<ItemEjercicio> items = new ArrayList<>();

    @Override
    public boolean nuevo(Object objeto) {
        if (objeto == null || !(objeto instanceof ItemEjercicio)) {
            return false;
        }
        ItemEjercicio item = (ItemEjercicio) objeto;
        if (!existe(item)) {
            items.add(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean editar(int pos, Object objeto) {
        if (objeto == null || !(objeto instanceof ItemEjercicio) || pos < 0 || pos >= items.size()) {
            return false;
        }
        items.set(pos, (ItemEjercicio) objeto);
        return true;
    }

    @Override
    public Object buscar(String credencial) {
        if (credencial == null) return null;

        for (ItemEjercicio item : items) {
            if (item.getEjercicio() != null && item.getEjercicio().getEnunciado().equalsIgnoreCase(credencial)) {
                return item;
            }
        }
        return null;
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
    public boolean eliminar(int pos) {
        if (pos >= 0 && pos < items.size()) {
            items.remove(pos);
            return true;
        }
        return false;
    }
}