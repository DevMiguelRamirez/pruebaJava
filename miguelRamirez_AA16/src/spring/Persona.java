package spring;

public class Persona {

	private String nombrePersona;

	public Persona() {
		nombrePersona = "";
	}

	public Persona(String nombrePersona) {
		super();
		this.nombrePersona = nombrePersona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombre) {
		this.nombrePersona = nombre;
	}


}
