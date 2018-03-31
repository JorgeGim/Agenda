package modelo;

import java.util.List;
import dto.ContactoDTO;
import persistencia.dao.interfaz.ContactoDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;

public class Contacto {
	
	private ContactoDAO contacto;
	
	public Contacto(DAOAbstractFactory metodo_persistencia)
	{
		this.contacto = metodo_persistencia.createContactoDAO();
	}
	
	public void agregarContacto(ContactoDTO nuevaLocalidad)
	{
		this.contacto.insert(nuevaLocalidad);
	}

	public void borrarContacto(ContactoDTO contacto_a_eliminar) 
	{
		this.contacto.delete(contacto_a_eliminar);
	}
	
	public List<ContactoDTO> obtenerContactos()
	{
		return this.contacto.readAll();		
	}
	
	public void editar(ContactoDTO contactoEditado , int idContactoAEditar) {
		this.contacto.editar(contactoEditado,idContactoAEditar);
	}
	
	public ContactoDTO obtenerContacto(int contacto) {
		return this.contacto.obtenerContacto(contacto);
	}

	public int obtenerId(String nombreContacto) {
		return this.contacto.obtenerId(nombreContacto);
	}
}
