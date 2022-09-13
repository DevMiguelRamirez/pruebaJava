package spring;

public class Artista extends Persona implements Recaudacion {

	private String nombreArtista;
	private String cancion;
	private double visitas;
	private double recaudacion;

	public Artista() {
		nombreArtista = "";
		cancion = "";
		visitas = 0;
		recaudacion = 0;
	}

	public Artista(String nombreArtista, double visitas, double recaudacion, String cancion) {
		super();
		this.nombreArtista = nombreArtista;
		this.cancion = cancion;
		this.visitas = visitas;
		this.recaudacion = recaudacion;
	}

	public String getNombreArtista() {
		return nombreArtista;
	}

	public String getCancion() {
		return cancion;
	}

	public void setCancion(String cancion) {
		this.cancion = cancion;
	}

	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}

	public double getVisitas() {
		return visitas;
	}

	public void setVisitas(double visitas) {
		this.visitas = visitas;
	}

	public double getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(double recaudacion) {
		this.recaudacion = recaudacion;
	}

	@Override
	public void calcRecaudacion() {
		recaudacion = visitas * 2;
	}

	@Override
	public String toString() {
		return cancion + " - " + nombreArtista + "	Visitas: " + visitas + "	Recaudacion: " + recaudacion;
	}

}
