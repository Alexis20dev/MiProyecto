package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Concepto;
import ec.edu.uce.miproyecto.dominio.Gestion;

import java.util.List;

public class ConceptoDAOMemoriaImpl implements InterfaceDAO{
    private static List<Concepto> conceptos = Gestion.getConceptos();
    @Override
    public boolean nuevo(Object objeto) {
        if(objeto == null || !(objeto instanceof Concepto)){
            return false;
        }
        Concepto concepto = (Concepto)objeto;
        if(existe(concepto)){
            System.out.println("El concepto ya existe");
            return false;
        }
        conceptos.add(concepto);
        return true;
    }

    @Override
    public boolean editar(int pos, Object objeto) {
        if (objeto == null || !(objeto instanceof Concepto) || pos < 0 || pos >= conceptos.size()) {
            return false;
        }
        conceptos.set(pos, (Concepto) objeto);
        return true;
    }

    @Override
    public Object buscar(String credencial) {
        if(credencial == null) return null;
        for (Concepto concepto : conceptos) {
            if(concepto.getNombre().equals(credencial)){
                return concepto;
            }
        }
        return null;
    }

    @Override
    public List listar() {
        return conceptos;
    }

    @Override
    public boolean existe(Object objeto) {
        if(objeto == null || !(objeto instanceof Concepto)){
            return false;
        }
        Concepto concepto = (Concepto)objeto;
        for (Concepto c: conceptos){
            if(c.equals(concepto)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int pos) {
        if (pos >= 0 && pos < conceptos.size()) {
            conceptos.remove(pos);
            return true;
        }
        return false;
    }
}
