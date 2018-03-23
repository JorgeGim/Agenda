package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO localidad);
	
	public boolean delete(LocalidadDTO localidadAEliminar);
	
	public boolean editar(LocalidadDTO localidad);

	public List<LocalidadDTO> readAll();

	public PersonaDTO obtenerPersona(LocalidadDTO localidad);
	
}
