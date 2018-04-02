package persistencia.conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import persistencia.vista.VentanaConexion;

public class Conexion 
{
	public static Conexion instancia;
	private Connection connection;
	File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
	
	private Conexion()
	{
		try
		{	
			archivo = new File ("conexion\\conexionBase.txt");
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);
			this.connection = DriverManager.getConnection(br.readLine(),br.readLine(),br.readLine());
			System.out.println("Conexion exitosa");
	        
		}
		catch(Exception e)
		{
			System.out.println("Conexion fallida");
		}
	}
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{	
			System.out.println("Conexion");
			//instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			System.out.println("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}
}
