package bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import logica.Asignatura;
import logica.Estudiante;
import logica.Profesor;
import logica.Tarea;
import logica.Usuario;

public class Prueba {
	private static Properties prop = cargarConf();
	private static String url = "jdbc:mysql://localhost:3306/crud";
	private static Connection c = conectar();

	private static Properties cargarConf() {
		Properties prop = new Properties();
		String fileName = "bd.config";
		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop.load(fis);
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		} catch (IOException ex) {
			System.out.println("Error");
		}
		return prop;
	}

	private static Connection conectar() {
		Connection c;
		try {
			c = DriverManager.getConnection(url, prop.getProperty("username"), prop.getProperty("password"));
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Podemos separarlo en 2 metodos, uno q solo comprueba el nombre, y otro como
	// este
	public static Usuario buscarUsuario(String nombre, String contrasena) {
		String queryUsuarios = "SELECT *,'estudiante' FROM estudiantes WHERE nombre = ? AND contrasena = ? UNION SELECT *, 'profesor' FROM profesores WHERE nombre = ? AND contrasena = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(queryUsuarios);
			s.setString(1, nombre);
			s.setString(2, contrasena);
			s.setString(3, nombre);
			s.setString(4, contrasena);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				Usuario u;
				if (rs.getString(3).equals("estudiante")) {
					u = new Estudiante(rs.getString(1), rs.getString(2));
				} else {
					u = new Profesor(rs.getString(1), rs.getString(2));
				}
				return u;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Se puede borrar. Solo estoy probandolo
	public static boolean hayNombreUsuario(String nombre) {
		String queryUsuarios = "SELECT nombre FROM estudiantes WHERE nombre = ? UNION SELECT nombre FROM profesores WHERE nombre = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(queryUsuarios);
			s.setString(1, nombre);
			s.setString(2, nombre);
			ResultSet rs = s.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void signIn(String nombre, String contrasena) {
		String crearUsuario = "INSERT INTO estudiantes (nombre, contrasena) VALUES (?,?)";
		PreparedStatement s;
		try {
			s = c.prepareStatement(crearUsuario);
			s.setString(1, nombre);
			s.setString(2, contrasena);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void crearListaNombreTareas(ArrayList<String> tareas, String nombreAsig) {
		String query = "SELECT distinct nombre FROM tareas where nombre_asignatura = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreAsig);
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				tareas.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Asignatura buscarAsignaturaEstudiante(String nombreAsig){
		String query = "SELECT nombre FROM asignaturas where nombre = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreAsig);
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Asignatura asig = new Asignatura();
				asig.setNombre(rs.getString(1));
				return asig;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void unirseAsignatura(String nombreEstudiante, String nombreAsig){
		String unirseQuery = "INSERT INTO cursos (nombre_asignatura, nombre_estudiante) "
				+ "SELECT ?, ? WHERE NOT EXISTS "
				+ "(SELECT 1 FROM cursos WHERE nombre_asignatura = ? AND nombre_estudiante = ?)";
		PreparedStatement s;
		try {
			s = c.prepareStatement(unirseQuery);
			s.setString(1, nombreAsig);
			s.setString(2, nombreEstudiante);
			s.setString(3, nombreAsig);
			s.setString(4, nombreEstudiante);
			s.executeUpdate();
			System.out.println(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Asignatura buscarAsignaturaProfesor(String nombreprof){
		String query = "SELECT nombre FROM asignaturas where nombreprof = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreprof);
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Asignatura asig = new Asignatura();
				asig.setNombre(rs.getString(1));
				return asig;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void buscarEntregas(ArrayList<Tarea> tareas, String nombreTarea, String nombreAsig) {
		String query = "SELECT nombre_estudiante, entregado_fecha, nota FROM tareas where nombre = ? and nombre_asignatura = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreTarea);
			s.setString(2, nombreAsig);		
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Tarea tarea = new Tarea(nombreTarea,rs.getString(1), rs.getString(2), rs.getDouble(3));
				tareas.add(tarea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void buscarTareasEstudiante(ArrayList<Tarea> tareas, String nombreAsig) {
		String query = "SELECT DISTINCT nombre, descripcion FROM tareas WHERE nombre_asignatura = ?;";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreAsig);		
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Tarea tarea = new Tarea(rs.getString(1),rs.getString(2));
				tareas.add(tarea);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ponerNotaEstudiante(Double nota, String nombreTarea, String nombreEstud, String nombreAsig) {
		String query =  "UPDATE tareas SET nota = ? WHERE nombre = ? and nombre_asignatura = ? and nombre_estudiante = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setDouble(1, nota);
			s.setString(2, nombreTarea);			
			s.setString(3, nombreAsig);
			s.setString(4, nombreEstud);	
			s.executeUpdate();
			System.out.println(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void entregarTarea(String nombreTarea, String nombreEstud, String nombreAsig) {
		String query =  "insert into tareas (nombre, nombre_asignatura, nombre_estudiante) values (?,?,?)";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreTarea);			
			s.setString(2, nombreAsig);
			s.setString(3, nombreEstud);	
			s.executeUpdate();
			System.out.println(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
