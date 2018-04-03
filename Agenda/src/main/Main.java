package main;

import modelo.Agenda;
import modelo.Conexiones;
import modelo.Contacto;
import modelo.Localidad;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaConexion;
import presentacion.vista.Vista;


public class Main 
{

	public static void main(String[] args) 
	{
		if(Conexiones.comprobarConexion()) {
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Localidad modelo_localidad = new Localidad(new DAOSQLFactory());
		Contacto modelo_contacto = new Contacto(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo,modelo_localidad,modelo_contacto);
		controlador.inicializar();
		}
	}
}
