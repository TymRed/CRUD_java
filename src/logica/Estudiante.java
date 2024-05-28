package logica;

public class Estudiante extends Usuario{
	private int cantidadAsig;

	public Estudiante() {
		super();
	}
	public Estudiante(String nombre, String contrasena) {
		super(nombre, contrasena);
	}
	
	
	public int getCantidadAsig() {
		return cantidadAsig;
	}
	public void setCantidadAsig(int cantidadAsig) {
		this.cantidadAsig = cantidadAsig;
	}

}
