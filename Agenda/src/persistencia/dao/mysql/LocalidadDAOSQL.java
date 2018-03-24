package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;


public class LocalidadDAOSQL implements LocalidadDAO {
	private static final String insert = "INSERT INTO localidades(idLocalidad,NombreLocalidad)" + "VALUES(?, ?)";
	private static final String delete = "DELETE FROM localidades WHERE idLocalidad = ?";
	private static final String readall = "SELECT * FROM localidades";
	private static final String editar = "UPDATE localidades SET (NombreLocalidad)\" + \"VALUES( ?) WHERE idLocalidad = ?" ;
	private static final String obtenerLocalidad = "SELECT * FROM localidades l WHERE l.idLocalidad = ?";
	private static final String obtenerId = "SELECT idLocalidad FROM localidades l WHERE l.NombreLocalidad = ?";
	
	public boolean insert(LocalidadDTO localidad) {
			PreparedStatement statement;
			Conexion conexion = Conexion.getConexion();
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setInt(1, localidad.getIdLocalidad());
				statement.setString(2, localidad.getNombre());
				
				
				if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
					return true;
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return false;
		
	}

	public boolean delete(LocalidadDTO localidadAEliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(localidadAEliminar.getIdLocalidad()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean editar(LocalidadDTO localidad) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(editar);
			statement.setString(1, localidad.getNombre());
			statement.setInt(2, obtenerID(localidad.getNombre()));

			
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	private int obtenerID(String nombre) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("SELECT idLocalidad FROM localidad l WHERE l.NombreLocalidad = ?");
			statement.setString(1, nombre);
			
			resultSet = statement.executeQuery();
			
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return resultSet.getInt("idLocalidad");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
		
	}

	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				localidades.add(new LocalidadDTO(resultSet.getInt("idLocalidad"), resultSet.getString("NombreLocalidad")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidades;
	}

	@Override
	public LocalidadDTO obtenerLocalidad(int idLocalidad) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(obtenerLocalidad);
			statement.setInt(1, idLocalidad);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				LocalidadDTO localidad = new LocalidadDTO(resultSet.getInt("idLocalidad"), resultSet.getString("NombreLocalidad"));
				return localidad;	
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int obtenerId(String nombreLocalidad) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(obtenerId);
			statement.setString(1, nombreLocalidad);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				return resultSet.getInt("idLocalidad");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}



}