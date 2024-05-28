package bd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import logica.Asignatura;
import logica.Estudiante;
import logica.Profesor;
import logica.Tarea;
import logica.Usuario;

public class BaseQueries {
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

	
	
	public static ArrayList<String> crearListaNombreTareas(String nombreAsig) {
		ArrayList<String> tareas = new ArrayList<>();
		String query = "SELECT nombre FROM tareasinfo where nombre_asignatura = ?";
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
		return tareas;
	}

	public static Asignatura buscarAsignaturaEstudiante(String nombreAsig) {
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

	public static String imprimirBoletinClase(String nombreAsig) {
		String query = "SELECT nombre, nombre_estudiante, nota FROM tareas WHERE nombre_asignatura = ?  ORDER BY nombre_estudiante ASC";
		PreparedStatement s;
		String boletin = "\t\t" + nombreAsig + "\n";
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreAsig);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				boletin += String.format("%s\t%s\t%.2f\n",rs.getString(1),rs.getString(2),rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boletin;
	}

	public static String imprimirMedias(String nombreAsig) {
		String query = "SELECT nombre_estudiante, AVG(nota) AS Nota_media FROM tareas WHERE nombre_asignatura = ? GROUP BY nombre_estudiante";
		String medias = "\n\tNotas medias\n";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreAsig);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				medias += rs.getString("nombre_estudiante") + ": " + rs.getDouble("Nota_media") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medias;
	}
	
	public static void imprimirBoletinHtml(String nombreAsig) {
		ArrayList<Tarea> tareas = buscarTareasEstudiante(nombreAsig);
		
		int numColumnas = tareas.size() + 2;
		ArrayList<String> estud = buscarEstudiantes(nombreAsig);
		
		File htmlResult = new File("boletin.html");
		
        try {
        	FileWriter fw = new FileWriter(htmlResult);
			fw.write("<!DOCTYPE html>");
			fw.write("<html>");
	        fw.write("<head>");
	        fw.write("<title>Notas finales " +nombreAsig + "</title>");
	        fw.write("</head>");
	        fw.write("<body>");
	        fw.write("<h2 style='margin-bottom:2px'>"+nombreAsig+"</h2>");
	        fw.write("<table border = '1' style='border-collapse: collapse;'>");
			
			String fila1 = "<tr>";
			for (int j = 0; j < numColumnas; j++) { //j es tarea
				if (j==0) {
					fila1 += "<th>Nombre</th>";
				}
				else if (j == numColumnas-1) {
					fila1 += "<th>Media</th>";
				}
				else {
					fila1+= "<th>" + tareas.get(j-1).getNombre() + "</th>";
				}
			}
			fw.write(fila1);
			
			for (int i = 0; i < estud.size(); i++) { //i es estudiante
				double notaTotal = 0;
				
				String fila = "<tr>";
				for (int j = 0; j < numColumnas; j++) {
					if (j==0) {
						fila += "<td>" + estud.get(i) + "</td>";
					}
					else if (j == numColumnas-1) {
						double notaMedia = notaTotal/(numColumnas-2);
						String color = notaMedia>=5 ? "lightgreen" : "lightcoral";
						fila += String.format("<th style='background-color:%s; padding: 2px 40px;'>%.2f</th>", color, notaMedia);
					}
					else {
						double nota = buscarNotaAsig(tareas.get(j-1).getNombre(), nombreAsig, estud.get(i));
						String color = nota >=5 ? "lightgreen" : "lightcoral";
						fila += String.format("<td style='background-color:%s;'>%.2f</td>", color, nota);

						notaTotal += nota;
					}
				}
				fw.write(fila);
			}
			
			fw.write("</table>");
	        fw.write("</body>");
	        fw.write("</html>");
	        fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Double buscarNotaAsig(String nombre, String nombreAsig, String nombreEstud) {
        String query = "SELECT nota FROM tareas where nombre = ? and nombre_asignatura = ? AND nombre_estudiante = ?";
        PreparedStatement s;
        try {
            s = c.prepareStatement(query);
            s.setString(1, nombre);
            s.setString(2, nombreAsig);
            s.setString(3, nombreEstud);
//            System.out.println(s);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
	}

	private static ArrayList<String> buscarEstudiantes(String nombreAsig) {
		ArrayList<String> nombEstud = new ArrayList<String>();

        String query = "SELECT distinct nombre_estudiante FROM cursos where nombre_asignatura = ?";
        PreparedStatement s;
        try {
            s = c.prepareStatement(query);
            s.setString(1, nombreAsig);
            System.out.println(s);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                nombEstud.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombEstud;
	}

	public static ArrayList<Tarea> buscarEntregas(String nombreTarea, String nombreAsig) {
        ArrayList<Tarea> tareas = new ArrayList<Tarea>();

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
        return tareas;
    }

	public static ArrayList<Tarea> buscarTareasEstudiante(String nombreAsig) {
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		String query = "SELECT nombre FROM tareasinfo WHERE nombre_asignatura = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreAsig);		
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Tarea tarea = new Tarea(rs.getString(1));
				tareas.add(tarea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tareas;
	}

	public static String buscarSiEntregado(String nombreTarea, String nombreEstud, String nombreAsig) { 
		String query = "SELECT entregado_fecha FROM tareas WHERE nombre = ? and nombre_estudiante = ? and  nombre_asignatura = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreTarea);			
			s.setString(2, nombreEstud);			
			s.setString(3, nombreAsig);
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
            e.printStackTrace();
        }

    }
	
	public static void entregarTarea(String nombreTarea, String nombreEstud, String nombreAsig) {
		String query =  "insert into tareas (nombre, nombre_asignatura, nombre_estudiante, entregado_fecha) values (?,?,?,?)";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreTarea);			
			s.setString(2, nombreAsig);
			s.setString(3, nombreEstud);	
			s.setString(4, LocalDate.now().toString());
			s.executeUpdate();
			System.out.println(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearTarea(String nombreAsig) {
		int maxTarea = buscarMaxIdTarea(nombreAsig) +1;
		String query =  "insert into tareasinfo (nombre, nombre_asignatura) values (?,?)";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, "Tarea" + maxTarea);			
			s.setString(2, nombreAsig);
			s.executeUpdate();
			System.out.println(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarTarea(String nombreTarea, String nombreAsig) {
		String query =  "delete from tareasinfo where nombre = ? AND nombre_asignatura = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);
			s.setString(1, nombreTarea);			
			s.setString(2, nombreAsig);
			s.executeUpdate();
			System.out.println(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int buscarMaxIdTarea(String nombreAsig) { 
		String query =  "select right(Max(nombre),1) from tareasinfo WHERE nombre_asignatura = ?";
		PreparedStatement s;
		try {
			s = c.prepareStatement(query);	
			s.setString(1, nombreAsig);
			System.out.println(s);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
