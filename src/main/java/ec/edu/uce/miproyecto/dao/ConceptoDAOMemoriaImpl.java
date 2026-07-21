package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Concepto;
import ec.edu.uce.miproyecto.dominio.Gestion;
import java.util.ArrayList;
import java.util.List;

public class ConceptoDAOMemoriaImpl implements InterfaceDAO {
    private static List<Concepto> conceptos = new ArrayList<>();

    @Override
    public boolean nuevo(Object objeto) throws DAOException {
        if (objeto == null || !(objeto instanceof Concepto)) {
            throw new DAOException("Los datos del concepto son inválidos o nulos.");
        }

        Concepto concepto = (Concepto) objeto;
        if (existe(concepto)) {
            throw new DAOException("El concepto ya se encuentra registrado.");
        }

        try {
            conceptos.add(concepto);
            return true;
        } catch (Exception e) {
            throw new DAOException("Error al guardar el concepto en memoria.", e);
        }
    }

    @Override
    public boolean editar(int pos, Object objeto) throws DAOException {
        if (objeto == null || !(objeto instanceof Concepto)) {
            throw new DAOException("Los datos para editar el concepto no son válidos.");
        }
        if (pos < 0 || pos >= conceptos.size()) {
            throw new DAOException("Posición fuera de rango para editar el concepto.");
        }
        try {
            conceptos.set(pos, (Concepto) objeto);
            return true;
        } catch (Exception e) {
            throw new DAOException("Error al actualizar los datos del concepto.", e);
        }
    }

    @Override
    public Object buscar(String credencial) throws DAOException {
        if (credencial == null || credencial.trim().isEmpty()) {
            throw new DAOException("La credencial de búsqueda no puede estar vacía.");
        }
        try {
            for (Concepto c : conceptos) {
                if (c.getNombre().equalsIgnoreCase(credencial)) {
                    return c;
                }
            }
            return null;
        } catch (Exception e) {
            throw new DAOException("Error durante la búsqueda del concepto.", e);
        }
    }

    @Override
    public List listar() {
        return conceptos;
    }

    @Override
    public boolean existe(Object objeto) {
        if (objeto == null || !(objeto instanceof Concepto)) {
            return false;
        }
        Concepto concepto = (Concepto) objeto;
        for (Concepto c : conceptos) {
            if (c.getNombre().equalsIgnoreCase(concepto.getNombre())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int pos) throws DAOException {
        if (pos < 0 || pos >= conceptos.size()) {
            throw new DAOException("No se puede eliminar. Posición inválida.");
        }
        try {
            conceptos.remove(pos);
            return true;
        } catch (Exception e) {
            throw new DAOException("Error al eliminar el concepto.", e);
        }
    }
}