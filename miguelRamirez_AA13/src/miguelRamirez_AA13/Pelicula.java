package miguelRamirez_AA13;

public class Pelicula {

	private String nombre;
	private String recaudacion;

	public Pelicula(String nombre, String recaudacion) {
		super();
		this.nombre = nombre;
		this.recaudacion = recaudacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(String recaudacion) {
		this.recaudacion = recaudacion;
	}

	@Override
	public String toString() {
		return nombre + "	" + recaudacion;
	}

}
