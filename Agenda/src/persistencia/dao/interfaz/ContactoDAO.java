package persistencia.dao.interfaz;

import java.util.List;

import dto.ContactoDTO;
import dto.LocalidadDTO;

public interface ContactoDAO {
	
	public boolean insert(ContactoDTO contacto);
	
	public boolean delete(ContactoDTO contactoAEliminar);
	
	public boolean editar(ContactoDTO contacto, int idContactoAEditar);

	public List<ContactoDTO> readAll();

	public ContactoDTO obtenerContacto(int idContacto);
	
	public int obtenerId(String nombreContacto);

}
