package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO localidad);
	
	public boolean delete(LocalidadDTO localidadAEliminar);
	
	public boolean editar(LocalidadDTO localidad, int idLocalidadAEditar);

	public List<LocalidadDTO> readAll();

	public LocalidadDTO obtenerLocalidad(int localidad);
	
	public int obtenerId(String nombreLocalidad);

	
}
