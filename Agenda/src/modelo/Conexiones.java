package modelo;

import java.io.FileWriter;
import java.io.PrintWriter;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DAOAbstractFactory;
import presentacion.vista.VentanaConexion;

public class Conexiones {
	
	private static presentacion.vista.VentanaConexion vC;

	public Conexiones(DAOAbstractFactory metodo_persistencia) {
	}

	public static boolean comprobarConexion() {
		Conexion conexion=Conexion.getConexion();
		if (conexion == null) {
			vC=new VentanaConexion();
			return false;
		}
		return true;
	}
	
	public void guardarConexionBase(String ip, String puerto, String usuario, String contraseña) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("conexionBase.txt");
            pw = new PrintWriter(fichero);
                pw.println("jdbc:mysql://"+ip+":"+puerto+"/agenda");
                pw.println(usuario);
                pw.println(contraseña);
                

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
}
