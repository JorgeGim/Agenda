package main;

import modelo.Agenda;
import modelo.Contactos;
import modelo.Localidades;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;


public class Main 
{

	public static void main(String[] args) 
	{
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Localidades modelo_localidad = new Localidades(new DAOSQLFactory());
		Contactos modelo_contacto = new Contactos(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo,modelo_localidad,modelo_contacto);
		controlador.inicializar();
	}
}
