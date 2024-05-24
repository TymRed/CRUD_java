package logica;

public class Tarea {
	
	private String nombre;
	private String nombreEstudiante;
	private String fechaEntrega;
	private Double nota;
	private boolean enviado;
	
	public Tarea(String nombre, String nombreEstudiante, String fechaEntrega, double nota) {
		this.nombre = nombre;
		this.nombreEstudiante = nombreEstudiante;
		this.fechaEntrega = fechaEntrega;
		if (nota == 0) {
			this.nota = null;
		}
		else {
			this.nota = nota;			
		}
	}
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNombreEstudiante() {
		return nombreEstudiante;
	}
	public void setNombreEstudiante(String nombreAlumno) {
		this.nombreEstudiante = nombreAlumno;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}

}
