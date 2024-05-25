package logica;

public class Tarea {
	
	private String nombre;
	private String nombreEstudiante;
	private String fechaEntrega;
	private String descripcion;
	private Double nota;
	private boolean enviado;
	
	public Tarea(String nombre, String nombreEstudiante, String fechaEntrega, double nota) {
		this.nombre = nombre;
		this.nombreEstudiante = nombreEstudiante;
		this.fechaEntrega = fechaEntrega;
		this.nota = nota == 0 ? null : nota;
//		if (nota == 0) {
//			this.nota = null;
//		}
//		else {
//			this.nota = nota;			
//		}
	}
	public Tarea(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = this.descripcion == null ? "No hay descripcion" : descripcion;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isEnviado() {
		return enviado;
	}
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
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
