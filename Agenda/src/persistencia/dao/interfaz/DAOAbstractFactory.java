package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();
	
	public LocalidadDAO createLocalidadDAO();

	public ContactoDAO createContactoDAO();
	
	public ConexionesDAO createConexionDAO();
}
