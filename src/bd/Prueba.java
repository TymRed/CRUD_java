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

import logica.Estudiante;
import logica.Profesor;
import logica.Usuario;

public class Prueba {
	private static Properties prop = cargarConf();
	private static String url = "jdbc:mysql://localhost:3306/crud";
	private static Connection c = conectar();

	private static void querys() {
		String nombre, contrasena; // a borrar, junto con el config
		// Log in, sign in
		String query = String.format(
				"SELECT DISTINCT * FROM profesores, estudiantes WHERE nombre = '%s' AND contrasena = '%s'", nombre,
				contrasena);
		String insert = "INSERT INTO estudiantes (nombre, contrasena) VALUES ('%s','%s')";

		// (Profe Asignatura)
		query = String.format("SELECT DISTINCT * FROM asignaturas WHERE asignaturas.idprof = '%s'", profesor.getId());
		Asignatura a = c.createStatement().executeQuery(query).next(); // hay que modificarlo
		// (Profe Asignatura- Tarea)
		query = String.format(
				"SELECT DISTINCT estudiantes.nombre, tareas.* FROM estudiantes, tareas WHERE tareas.idasignatura = '%s'",
				asignatura.getId());
		// (Profe Asignatura- Tarea)
		insert = "INSERT INTO estudiantes (nombre, contrasena) VALUES ('%s','%s')";
		query = String.format(
				"SELECT DISTINCT * FROM profesores, estudiantes WHERE nombre = '%s' AND contrasena = '%s'", nombre,
				contrasena);
		insert = "INSERT INTO estudiantes (nombre, contrasena) VALUES ('%s','%s')";
	}

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

	// Podemos separarlo en 2 metodos, uno q solo comprueba el nombre, y otro como este
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
				}
				else {
					u = new Profesor(rs.getString(1), rs.getString(2));
				}
				return u;
				
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Se puede borrar. Solo estoy probandolo
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

	public static void crearListaNombreTareas(ArrayList<String> tareas) {
		String query = "SELECT distinct nombre FROM tareas";
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				tareas.add(rs.getString(1));		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void buscarTareas(ArrayList<ArrayList<String>> tareas) {
		String query = "SELECT nombre_estudiante, entregado_fecha FROM tareas where nombre = 'Tarea1' and nombre_asignatura = 'Prog'";
		try {
			int i = 0;                               // Hay que mejorar!!!!!!!!!!
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				tareas.add(new ArrayList<>());
				tareas.get(i).add(rs.getString(1));
				tareas.get(i).add(rs.getString(2));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
