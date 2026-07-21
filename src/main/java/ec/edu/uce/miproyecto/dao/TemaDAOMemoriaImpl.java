package ec.edu.uce.miproyecto.dao;

import ec.edu.uce.miproyecto.dominio.Concepto;
import ec.edu.uce.miproyecto.dominio.Ejercicio;
import ec.edu.uce.miproyecto.dominio.Gestion;
import ec.edu.uce.miproyecto.dominio.Tema;

import java.util.List;

public class TemaDAOMemoriaImpl implements InterfaceDAO,TemaDAO {
    private static List<Tema> temas= Gestion.getTemas();
    @Override
    public boolean nuevo(Object objeto) {
        if(objeto == null || !(objeto instanceof Tema)){
            return false;
        }
        Tema tema = (Tema)objeto;
        if(existe(tema)){
            System.out.println("El tema ya existe");
            return false;
        }
        temas.add(tema);
        return true;
    }

    @Override
    public boolean editar(int pos, Object objeto) {
        if (objeto == null || !(objeto instanceof Tema) || pos < 0 || pos >= temas.size()) {
            return false;
        }
        temas.set(pos, (Tema)objeto);
        return true;
    }

    @Override
    public Object buscar(String credencial) {
        if(credencial == null) return null;
        for (Tema tema : temas) {
            if(tema.getNombre().equals(credencial)){
                return tema;
            }
        }
        return null;
    }

    @Override
    public List listar() {
        return temas;
    }

    @Override
    public boolean existe(Object objeto) {
        if (objeto == null || !(objeto instanceof Tema)) {
            return false;
        }
        Tema tema = (Tema)objeto;
        for(Tema tem:temas){
            if(tema.equals(tem)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int pos) {
        if(pos < 0 || pos >= temas.size()){
            temas.remove(pos);
            return true;
        }
        return false;
    }

    @Override
    public boolean agregarConcepto(Tema tema, Concepto concepto) {
        if (tema == null || concepto == null) {
            return false;
        }

        tema.getConceptos().add(concepto);
        return true;
    }

    @Override
    public boolean agregarEjercicio(Tema tema, Ejercicio ejercicio) {
        if (tema == null || ejercicio == null) {
            return false;
        }

        tema.getEjercicios().add(ejercicio);
        return true;
    }
}
