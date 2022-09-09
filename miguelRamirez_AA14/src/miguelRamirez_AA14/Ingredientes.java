package miguelRamirez_AA14;

public class Ingredientes {

	String ingredientes;

	public Ingredientes(String ingredientes) {
		super();
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return ingredientes;
	}

	public void setNombre(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		return ingredientes;
	}

}
