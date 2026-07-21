package ec.edu.uce.miproyecto.dao;

public class ConceptoDAOFabrica {

    public InterfaceDAO crearConceptoDAO() {
        return new ConceptoDAOMemoriaImpl();
    }
}