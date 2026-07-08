package ec.edu.uce.miproyecto.dao;

public class EjercicioDAOFabrica {
    public EjercicioDAO crearEjercicioDAO(){
        return new EjercicioDAOMemoriaImpl();
    }
}
