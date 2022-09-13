package spring;

public class Usuario {

	private String nombreUsuario;

	public Usuario() {
		nombreUsuario = "";
	}

	public Usuario(String nombreUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombre() {
		return nombreUsuario;
	}

	public void setNombre(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
