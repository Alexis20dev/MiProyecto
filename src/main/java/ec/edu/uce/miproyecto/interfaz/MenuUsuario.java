package ec.edu.uce.miproyecto.interfaz;

import ec.edu.uce.miproyecto.dao.InterfaceDAO;
import ec.edu.uce.miproyecto.dao.UsuarioDAOFabrica;
import ec.edu.uce.miproyecto.dao.DAOException;
import ec.edu.uce.miproyecto.dominio.*;
import ec.edu.uce.miproyecto.util.Consola;
import ec.edu.uce.miproyecto.util.Validaciones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class MenuUsuario {

    private final BufferedReader reader;
    private final Estudiante estudiante;
    private final Ejercicio ejercicio;
    private final InterfaceDAO usuarioDao = new UsuarioDAOFabrica().crearUsuarioDAO();

    public MenuUsuario(Estudiante estudiante, Ejercicio ejercicio, BufferedReader reader) {
        this.estudiante = estudiante;
        this.ejercicio = ejercicio;
        this.reader = reader;
    }

    public MenuUsuario(Estudiante estudiante, Ejercicio ejercicio) {
        this(estudiante, ejercicio, new BufferedReader(new InputStreamReader(System.in)));
    }

    public void mostrarMenuUsuario() {


        }
    }
