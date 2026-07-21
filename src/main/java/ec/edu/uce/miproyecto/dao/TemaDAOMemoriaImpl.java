package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Concepto;
import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dominio.Gestion;
import ec.edu.uce.miproyecto.dominio.Tema;

import java.util.List;

public class TemaDAOMemoriaImpl implements TemaDAO {

    private List<Tema> temas = Gestion.getTemas();

    @Override
    public boolean nuevo(Tema tema) throws DAOException {
        if (tema == null) {
            throw new DAOException("El tema no puede ser nulo.");
        }
        if (existe(tema)) {
            throw new DAOException("El tema ya se encuentra registrado.");
        }
        return temas.add(tema);
    }

    @Override
    public boolean agregarConcepto(Tema tema, Concepto concepto) throws DAOException {
        if (tema == null || concepto == null) {
            throw new DAOException("El tema o el concepto no pueden ser nulos.");
        }
        if (tema.getConceptos().contains(concepto)) {
            throw new DAOException("El concepto '" + concepto.getNombre() + "' ya existe en este tema.");
        }
        tema.getConceptos().add(concepto);
        return true;
    }

    @Override
    public boolean agregarEjercicio(Tema tema, Ejercicio ejercicio) throws DAOException {
        if (tema == null || ejercicio == null) {
            throw new DAOException("El tema o el ejercicio no pueden ser nulos.");
        }
        if (tema.getEjercicios().contains(ejercicio)) {
            throw new DAOException("El ejercicio ya existe en este tema.");
        }
        tema.getEjercicios().add(ejercicio);
        return true;
    }

    @Override
    public boolean editar(int pos, Tema tema) throws DAOException {
        if (pos < 0 || pos >= temas.size()) {
            throw new DAOException("Posición fuera de rango.");
        }
        temas.set(pos, tema);
        return true;
    }

    @Override
    public Tema buscar(String credencial) throws DAOException {
        for (Tema t : temas) {
            if (t.getNombre().equalsIgnoreCase(credencial)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Tema> listar() {
        return temas;
    }

    @Override
    public boolean existe(Tema tema) {
        if (tema == null) return false;
        for (Tema t : temas) {
            if (t.getNombre().equalsIgnoreCase(tema.getNombre())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int pos) throws DAOException {
        if (pos < 0 || pos >= temas.size()) {
            throw new DAOException("Posición fuera de rango para eliminar.");
        }
        temas.remove(pos);
        return true;
    }
}