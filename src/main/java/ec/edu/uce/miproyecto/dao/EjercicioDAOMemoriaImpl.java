package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dominio.Gestion;
import ec.edu.uce.miproyecto.dominio.Tema;

import java.util.List;

public class EjercicioDAOMemoriaImpl implements InterfaceDAO {

    private static List<Ejercicio> ejercicios = Gestion.getEjercicios();

    @Override
    public boolean nuevo(Object objeto) throws DAOException {
        if (objeto == null || !(objeto instanceof Ejercicio)) {
            throw new DAOException("Los datos del ejercicio son inválidos o nulos.");
        }

        Ejercicio ejercicio = (Ejercicio) objeto;
        if (existe(ejercicio)) {
            throw new DAOException("El ejercicio ya se encuentra registrado.");
        }

        try {
            ejercicios.add(ejercicio);
            return true;
        } catch (Exception e) {
            throw new DAOException("Error al guardar el ejercicio en memoria.", e);
        }
    }

    @Override
    public boolean editar(int pos, Object objeto) {
        if (objeto == null || !(objeto instanceof Ejercicio) || pos < 0 || pos >= ejercicios.size()) {
            return false;
        }
        ejercicios.set(pos, (Ejercicio) objeto);
        return true;
    }

    @Override
    public Object buscar(String credencial) {
        if (credencial == null) return null;
        for (Ejercicio e : ejercicios) {
            if (e.getEnunciado().equalsIgnoreCase(credencial)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List listar() {
        return ejercicios;
    }

    @Override
    public boolean existe(Object objeto) {
        if (objeto == null || !(objeto instanceof Ejercicio)) {
            return false;
        }
        return ejercicios.contains(objeto);
    }

    @Override
    public boolean eliminar(int pos) {
        if (pos >= 0 && pos < ejercicios.size()) {
            ejercicios.remove(pos);
            return true;
        }
        return false;
    }
}