package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;

public class Localidades {
	private LocalidadDAO localidad;
	
	public Localidades(DAOAbstractFactory metodo_persistencia)
	{
		this.localidad = metodo_persistencia.createLocalidadDAO();
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		this.localidad.insert(nuevaLocalidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar) 
	{
		this.localidad.delete(localidad_a_eliminar);
	}
	
	public List<LocalidadDTO> obtenerLocalidades()
	{
		return this.localidad.readAll();		
	}
	
	public void editar(LocalidadDTO localidadEditada) {
		this.localidad.editar(localidadEditada);
	}
	
	public LocalidadDTO obtenerLocalidad(int localidad) {
		return this.localidad.obtenerLocalidad(localidad);
	}

	public int obtenerId(String nombreLocalidad) {
		return this.localidad.obtenerId(nombreLocalidad);
	}

	
}
