package miguelRamirez_AA14;

public interface Produccion {

	public static boolean produccionActiva(int temperatura) {
		boolean produccion = false;

		if (temperatura <= 40) {
			produccion = false;
		} else {
			produccion = true;
		}

		return produccion;
	}

}
