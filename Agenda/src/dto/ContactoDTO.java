package dto;

public class ContactoDTO {

	private int idContacto;
	private String nombre;

	public ContactoDTO(int idContacto,String nombre) {
		this.idContacto=idContacto;
		this.nombre=nombre;
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
