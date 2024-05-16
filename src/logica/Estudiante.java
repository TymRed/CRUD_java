package logica;

public class Estudiante extends Usuario{
	private int cantidadAsig;

	
	public Estudiante() {
		super();
	}
	public Estudiante(int id, String nombre, String contrasena, int cantidadAsig) {
		super(id, nombre, contrasena);
		this.cantidadAsig = cantidadAsig;
	}
	
	
	public int getCantidadAsig() {
		return cantidadAsig;
	}
	public void setCantidadAsig(int cantidadAsig) {
		this.cantidadAsig = cantidadAsig;
	}

}
