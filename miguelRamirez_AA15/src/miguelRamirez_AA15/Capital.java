package miguelRamirez_AA15;

public class Capital extends Provincia {

	private String nombreCapital;

	public Capital(String nombreProvincia, String nombreCapital) {
		super(nombreProvincia);
		this.nombreCapital = nombreCapital;
	}

	public String getNombreCapital() {
		return nombreCapital;
	}

	public void setNombreCapital(String nombreCapital) {
		this.nombreCapital = nombreCapital;
	}

	@Override
	public String toString() {
		return "Capital de " + nombreCapital + ": " + getNombreProvincia();
	}


}
