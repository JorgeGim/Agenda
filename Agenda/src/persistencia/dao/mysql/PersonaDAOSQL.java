package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, depto, idLocalidad, email, fechaDeCumpleaños, TipoContacto)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM agenda.personas";
	private static final String editar = "UPDATE personas SET (idPersona, nombre, telefono, calle, altura, piso, depto, localidad, email, fechaDeCumpleaños, TipoContacto)\" + \"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE idPersona = ?" ;
	private static final String obtenerPersona= "SELECT * FROM PERSONAS p WHERE p.idPersona = ?";
	
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getCalle());
			statement.setString(5, persona.getAltura());
			statement.setString(6, persona.getPiso());
			statement.setString(7, persona.getDepto());
			statement.setInt(8, persona.getLocalidad());
			statement.setString(9, persona.getEmail());
			statement.setString(10, persona.getFechaCumpleaños());
			statement.setString(11, persona.getTipoContacto());
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutÃ³ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono"),resultSet.getString("Calle"),resultSet.getString("Altura"),resultSet.getString("Piso"),resultSet.getString("Depto"),resultSet.getInt("idLocalidad"),resultSet.getString("Email"),resultSet.getString("FechaDeCumpleaños"),resultSet.getString("TipoContacto")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}

	@Override
	public boolean editar(PersonaDTO persona, int id_persona) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(editar);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getCalle());
			statement.setString(5, persona.getAltura());
			statement.setString(6, persona.getPiso());
			statement.setString(7, persona.getDepto());
			statement.setInt(8, persona.getLocalidad());
			statement.setString(9, persona.getEmail());
			statement.setString(10, persona.getFechaCumpleaños());
			statement.setString(11, persona.getTipoContacto());
			statement.setString(12, Integer.toString(id_persona));
			
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
	public PersonaDTO obtenerPersona(PersonaDTO persona) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerPersona);
			statement.setString(1, Integer.toString(persona.getIdPersona()));
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				persona=new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono"),resultSet.getString("Calle"),resultSet.getString("Altura"),resultSet.getString("Piso"),resultSet.getString("Depto"),resultSet.getInt("idLocalidad"),resultSet.getString("Email"),resultSet.getString("FechaDeCumpleaños"),resultSet.getString("TipoContacto"));
			}
		//aca cambio	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return persona;
	}
}
