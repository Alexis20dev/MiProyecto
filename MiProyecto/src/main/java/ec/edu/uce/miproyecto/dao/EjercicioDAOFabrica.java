package ec.edu.uce.miproyecto.dao;

public class EjercicioDAOFabrica {
    public InterfaceDAO crearEjercicioDAO() {
        return new EjercicioDAOMemoriaImpl();
    }
}