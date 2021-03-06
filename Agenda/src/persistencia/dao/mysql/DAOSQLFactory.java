/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.ConexionesDAO;
import persistencia.dao.interfaz.ContactoDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;

public class DAOSQLFactory implements DAOAbstractFactory 
{
	/* (non-Javadoc)
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */
	@Override
	public PersonaDAO createPersonaDAO() 
	{
				return new PersonaDAOSQL();
	}
	
	@Override
	public LocalidadDAO createLocalidadDAO() {
			return new LocalidadDAOSQL();
	}

	@Override
	public ContactoDAO createContactoDAO() {
			return new ContactoDAOSQL();
	}

	@Override
	public ConexionesDAO createConexionDAO() {
		return new ConexionDAOSQL();
	}

}
