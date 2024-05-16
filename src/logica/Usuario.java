package logica;

public class Usuario {
	protected int id;
	protected String nombre;
	protected String contrasena;
	
	
	public Usuario(int id, String nombre, String contrasena) {
		this.id = id;
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	public Usuario() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
