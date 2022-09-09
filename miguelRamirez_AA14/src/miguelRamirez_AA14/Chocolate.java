package miguelRamirez_AA14;

public class Chocolate {

	String nombre;
	int cantidad;
	Ingredientes ingredientes;

	public Chocolate(String nombre, int cantidad, Ingredientes ingredientes) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ingredientes getingrediente() {
		return ingredientes;
	}

	public void setingrediente(Ingredientes ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		return "Tipo: " + nombre + ", cantidad: " + cantidad + ", ingredientes: " + ingredientes.toString();
	}

}
