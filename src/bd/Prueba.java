package bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Prueba {
	private static Properties prop = loadProps();
	private static String url = "jdbc:mysql://localhost:3306/crud";
	private static Connection c = connect();
	
	
	private static void querys() {
		String nombre, contrasena; //a borrar, junto con el config
		//Log in, sign in
		String query = String.format("SELECT DISTINCT * FROM profesores, alumnos WHERE nombre = '%s' AND contrasena = '%s'", nombre, contrasena);
		String insert = "INSERT INTO alumnos (nombre, contrasena) VALUES ('%s','%s')";
		
		//(Profe Asignatura)
		query = String.format("SELECT DISTINCT * FROM asignaturas WHERE asignaturas.idprof = '%s'", profesor.getId());
		Asignatura a = c.createStatement().executeQuery(query).next(); //hay que modificarlo
		//(Profe Asignatura- Tarea)
		query = String.format("SELECT DISTINCT alumnos.nombre, tareas.* FROM alumnos, tareas WHERE tareas.idasignatura = '%s'", asignatura.getId());
		//(Profe Asignatura- Tarea)
		insert = "INSERT INTO alumnos (nombre, contrasena) VALUES ('%s','%s')";
		query = String.format("SELECT DISTINCT * FROM profesores, alumnos WHERE nombre = '%s' AND contrasena = '%s'", nombre, contrasena);	
		insert = "INSERT INTO alumnos (nombre, contrasena) VALUES ('%s','%s')";
	}
	
	
	private static Properties loadProps() {
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

	private static Connection connect() {
		Connection c;
		try {
			c = DriverManager.getConnection(url, prop.getProperty("username"), prop.getProperty("password"));
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Boolean for.. idc, testing mb
	boolean userExists(String nombre, String contrasena) {
		String queryUsuarios = String.format("SELECT DISTINCT * FROM profesores, alumnos "
				+ "WHERE nombre = '%s' AND contrasena = '%s'", nombre, contrasena);
		Statement s;
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(queryUsuarios);
			if (rs.next()) return true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	boolean signIn(String nombre, String contrasena) {
		if (userExists(nombre, contrasena))
			return false;
		
		String createUser = "INSERT INTO alumnos (nombre, contrasena) VALUES ('%s','%s')";
		Statement s;
		try {
			s = c.createStatement();
			s.executeUpdate(createUser);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
