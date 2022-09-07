package miguelRamirez_AA9;

public class Videojuego implements Ventas {

	private String nombre;
	private int ventas;
	private double precioUnitario;

	public Videojuego(String nombre, int ventas, double precioUnitario) {
		super();
		this.nombre = nombre;
		this.ventas = ventas;
		this.precioUnitario = precioUnitario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Override
	public String toString() {
		return nombre + " ha vendido " + ventas + " unidades, con una valor por unidad de " + precioUnitario + " $"
				+ ", con un valor total de " + ventasTotales() + " $";
	}

	@Override
	public double ventasTotales() {
		return ventas * precioUnitario;
	}

}
