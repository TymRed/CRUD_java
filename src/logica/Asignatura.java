package logica;

public class Asignatura {

	private String nombre;
	private Double notaMedia; //Para poder hacer null

	public Asignatura() {
	}

	public Asignatura(String nombre, double notaMedia) {
		this.nombre = nombre;
		this.notaMedia = notaMedia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}

}
