package modelo;

import persistencia.conexion.Conexion;
import persistencia.vista.VentanaConexion;

public class Conexiones {
	
	private static persistencia.vista.VentanaConexion vC;

	private Conexiones() {
		
	}

	public static boolean comprobarConexion() {
		Conexion conexion=Conexion.getConexion();
		if (conexion == null) {
			vC=new VentanaConexion();
			return false;
		}
		return true;
	}
	
	
}
