package miguelRamirez_AA15;

public class Usuario {

	private String nombreUsuario;
	private int ID;

	public Usuario(String nombreUsuario, int ID) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.ID = ID;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
	
}
