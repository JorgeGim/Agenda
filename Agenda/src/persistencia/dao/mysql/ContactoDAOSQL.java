package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ContactoDTO;
import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ContactoDAO;

public class ContactoDAOSQL implements ContactoDAO {
	private static final String insert = "INSERT INTO tiposDeContactos(idTipo,DescripcionTipo)" + "VALUES(?, ?)";
	private static final String delete = "DELETE FROM tiposDeContactos WHERE idTipo = ?";
	private static final String readall = "SELECT * FROM tiposDeContactos";
	private static final String editar = "UPDATE tiposDeContactos SET DescripcionTipo = ? WHERE idTipo = ?" ;
	private static final String obtenerContacto = "SELECT * FROM tiposDeContactos tc WHERE tc.idTipo = ?";
	private static final String obtenerId = "SELECT idTipo FROM tiposDeContactos tc WHERE tc.descripcionTipo = ?";
	
	
	@Override
	public boolean insert(ContactoDTO contacto) {
		PreparedStatement statement;
		Conexion conexion = conectar();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, contacto.getIdContacto());
			statement.setString(2, contacto.getNombre());
			
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
}

	@Override
	public boolean delete(ContactoDTO contactoAEliminar) {
		PreparedStatement statement;
		Conexion conexion = conectar();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, contactoAEliminar.getIdContacto());
			if(statement.executeUpdate() > 0) //Si se ejecutÃ³ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean editar(ContactoDTO contacto, int idContactoAEditar) {
		PreparedStatement statement;
		Conexion conexion = conectar();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(editar);
			statement.setString(1, contacto.getNombre());
			statement.setInt(2, idContactoAEditar);

			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
}

	@Override
	public List<ContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ContactoDTO> contactos = new ArrayList<ContactoDTO>();
		Conexion conexion = conectar();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				contactos.add(new ContactoDTO(resultSet.getInt("idTipo"), resultSet.getString("DescripcionTipo")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return contactos;
	}

	@Override
	public ContactoDTO obtenerContacto(int idContacto) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = conectar();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(obtenerContacto);
			statement.setInt(1, idContacto);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				ContactoDTO contacto = new ContactoDTO(resultSet.getInt("idTipo"), resultSet.getString("DescripcionTipo"));
				return contacto;	
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
}

	@Override
	public int obtenerId(String nombreContacto) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = conectar();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(obtenerId);
			statement.setString(1, nombreContacto);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				return resultSet.getInt("idTipo");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private Conexion conectar() {
		return Conexion.getConexion();
	}
}
