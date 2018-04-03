package persistencia.conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import presentacion.vista.VentanaConexion;

public class Conexion 
{
	public static Conexion instancia;
	private Connection connection;
	static File archivo = null;
	static FileReader fr = null;
	static BufferedReader br = null;
	
	private Conexion(String ipPuerto, String usuario, String contraseña)
	{
		try
		{	
			this.connection = DriverManager.getConnection(ipPuerto,usuario,contraseña);
			
			
			System.out.println("Conexion exitosa");
	        
		}
		catch(Exception e)
		{
			System.out.println("Conexion fallida");
			new VentanaConexion();
			JOptionPane.showMessageDialog(null, "No se pudo conectar con la base");
			
		}
	}
	
	public static Conexion getConexion()   
	{	
		if(instancia == null)
		{	
			try {
			archivo = new File ("conexionBase.txt");
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);
	        
	        String linea;
			if((linea=br.readLine())== null) {
				System.out.println(" archivo vacio");
				return null;
			}
	        instancia= new Conexion(linea,br.readLine(),br.readLine());
			}
			catch(Exception e) {
				System.out.println("No se pudo acceder al archivo conexion base");
				
			}
			finally{
		         // En el finally cerramos el fichero, para asegurarnos
		         // que se cierra tanto si todo va bien como si salta 
		         // una excepcion.
		         try{                    
		            if( null != fr ){   
		               fr.close();     
		            }                  
		         }catch (Exception e2){ 
		            e2.printStackTrace();
		         }
		      }
			
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
