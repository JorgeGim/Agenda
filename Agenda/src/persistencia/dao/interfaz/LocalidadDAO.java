package persistencia.dao.interfaz;

import dto.LocalidadDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO localidad);
	
	public boolean delete(LocalidadDTO localidadAEliminar);
	
	public boolean editar(LocalidadDTO localidad);
	
}
