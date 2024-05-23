package logica;

public class Tarea {
	private String nombreAlumno;
	private String fechaEntrega;
	
	
	public Tarea(String nombreAlumno, String fechaEntrega) {
		this.nombreAlumno = nombreAlumno;
		this.fechaEntrega = fechaEntrega;
	}
	
	
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

}
