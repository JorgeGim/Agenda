package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextField;

import dto.ContactoDTO;
import dto.Entidad;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import modelo.Agenda;
import modelo.Contacto;
import modelo.Localidad;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaContacto;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import verificador.VerificadorDeDatos;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private List<LocalidadDTO> localidades_en_tabla;
		private List<ContactoDTO> contactos_en_tabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		private Localidad localidades;
		private Contacto contactos;
		private VentanaLocalidad ventanaLocalidad;
		private VentanaContacto ventanaContactos;
		private boolean agregando;
		
		public Controlador(Vista vista, Agenda agenda,Localidad localidad, Contacto contactos)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);
			this.vista.getBtnLocalidades().addActionListener(this);
			this.vista.getBtnContacto().addActionListener(this);
			this.agenda = agenda;
			this.localidades=localidad;
			this.contactos=contactos;
			this.personas_en_tabla = null;
			this.localidades_en_tabla=null;
			this.contactos_en_tabla=null;
		}
		
		public void inicializar()
		{
			this.llenarTabla();
			this.vista.show();
			this.ventanaLocalidad = new VentanaLocalidad(this);
			ControladorTipoContacto c = new ControladorTipoContacto();
			this.ventanaContactos = new VentanaContacto(this, c);
			c.setV(ventanaContactos);
		}
		
		public List<PersonaDTO> getPersonas_en_tabla() {
			return personas_en_tabla;
		}

		public List<LocalidadDTO> getLocalidades_en_tabla() {
			return localidades_en_tabla;
		}

		public List<ContactoDTO> getContactos_en_tabla() {
			return contactos_en_tabla;
		}

		public Contacto getContactos() {
			return contactos;
		}

		public Agenda getAgenda() {
			return agenda;
		}

		public Localidad getLocalidades() {
			return localidades;
		}

		protected void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				String local = this.localidades.obtenerLocalidad(this.personas_en_tabla.get(i).getLocalidad()).getNombre();
				String contact = this.contactos.obtenerContacto(this.personas_en_tabla.get(i).getTipoContacto()).getNombre();
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(),this.personas_en_tabla.get(i).getCalle(),this.personas_en_tabla.get(i).getAltura(),this.personas_en_tabla.get(i).getPiso(),this.personas_en_tabla.get(i).getDepto(),local,this.personas_en_tabla.get(i).getEmail(),this.personas_en_tabla.get(i).getFechaCumpleaños(),contact};
				this.vista.getModelPersonas().addRow(fila);
			}			
		}
		
		public void llenarTablaLocalidad() {
			this.ventanaLocalidad.getModelPersonas().setRowCount(0);
			this.ventanaLocalidad.getModelPersonas().setColumnCount(0);
			this.ventanaLocalidad.getModelPersonas().setColumnIdentifiers(this.ventanaLocalidad.getNombreColumna());
			
			this.localidades_en_tabla= localidades.obtenerLocalidades();
			for (int i = 0; i < this.localidades_en_tabla.size(); i ++)
			{
				Object[] filaLocalidades = {this.localidades_en_tabla.get(i).getNombre()};
				this.ventanaLocalidad.getModelPersonas().addRow(filaLocalidades);
			}
			
		}
		
		public void llenarTablaContacto() {
			this.ventanaContactos.getModelPersonas().setRowCount(0);
			this.ventanaContactos.getModelPersonas().setColumnCount(0);
			this.ventanaContactos.getModelPersonas().setColumnIdentifiers(this.ventanaContactos.getNombreColumna());
			
			this.contactos_en_tabla= contactos.obtenerContactos();
			for (int i = 0; i < this.contactos_en_tabla.size(); i ++)
			{
				Object[] filaContacto = {this.contactos_en_tabla.get(i).getNombre()};
				this.ventanaContactos.getModelPersonas().addRow(filaContacto);
			}
			
		}
		
		
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.vista.getBtnAgregar())
			{
				this.ventanaPersona = new VentanaPersona(this,this.localidades,this.contactos);
				this.agregando=true;
			}
			else if(e.getSource() == this.vista.getBtnBorrar())
			{
				int[] filas_seleccionadas = obtenerFila();
				for (int fila:filas_seleccionadas)
				{
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}
				//actualiza la tabla
				this.llenarTabla();
				
			}
			else if(e.getSource() == this.vista.getBtnReporte())
			{	/*List<Entidad> entidades=new ArrayList<Entidad>();
			
				for(int i=0; i< 10;i++) {
					entidades.add(new Entidad(i,"10/"+i+"/199"+i));
				}
				entidades.add(new Entidad(20,"20/10/1800"));
				entidades.add(new Entidad(21,"20/10/1800"));
				entidades.add(new Entidad(22,"20/10/1800"));
				entidades.add(new Entidad(23,"20/10/1800"));
				ReporteAgenda reporte=new ReporteAgenda(entidades);*/
				
				List<PersonaDTO> lista = agenda.obtenerPersonas();
				Collections.sort(lista);			
				ReporteAgenda reporte = new ReporteAgenda(lista);
				reporte.mostrar();				
			}
			else if(e.getSource() == this.vista.getBtnEditar())
			{
				int[] filas_seleccionadas = obtenerFila();
				for (int fila:filas_seleccionadas)
				{
					this.ventanaPersona = new VentanaPersona(this,this.localidades,this.contactos);
					this.agregando=false;
					PersonaDTO persona = this.agenda.obtenerPersona(this.personas_en_tabla.get(fila));
					
					this.ventanaPersona.setIdPersonaAEditar(persona.getIdPersona());
					this.ventanaPersona.setTxtNombre(persona.getNombre().toString());
					this.ventanaPersona.setTxtTelefono(persona.getTelefono().toString());
					this.ventanaPersona.setTxtCalle(persona.getCalle().toString());
					this.ventanaPersona.setTxtAltura(persona.getAltura().toString());
					this.ventanaPersona.setTxtPiso(persona.getPiso().toString());
					this.ventanaPersona.setTxtDepto(persona.getDepto().toString());
					this.ventanaPersona.setTxtLocalidad(this.localidades.obtenerLocalidad(persona.getLocalidad()).getNombre());
					this.ventanaPersona.setTxtEmail(persona.getEmail().toString());
					this.ventanaPersona.setTxtFechaDeCumpleaños(persona.getFechaCumpleaños().toString());
					this.ventanaPersona.setTxtTipoDeContacto(this.contactos.obtenerContacto(persona.getTipoContacto()).getNombre());
					
					//aca borra
					//this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
					//ventanaPersona.setTxtNombre(txtNombre);
					
				}
				//actualiza la tabla
				this.llenarTabla();
			
			}
			else if(e.getSource() == this.vista.getBtnLocalidades()) {
				//System.out.println("algo");
				
				llenarTablaLocalidad();
				this.ventanaLocalidad.show();

	
			}
			else if(e.getSource() == this.vista.getBtnContacto()) {
				
				llenarTablaContacto();
				ventanaContactos.show();
			}
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())
			{
				boolean checkEmail = true;
				boolean checkFechaDeNacimiento = true;
				
				String email = this.ventanaPersona.getTxtEmail().getText();
				String fechaDeNacimiento = this.ventanaPersona.getTxtFechaDeCumpleaños().getText();
				
				if(!email.isEmpty()) {
					checkEmail = verificarEmail();
				}
				
				if(!fechaDeNacimiento.isEmpty()) {
					checkFechaDeNacimiento = verificarFechaNacimiento();
				}
				
				boolean bandera = verificarNombreCalle() && checkEmail && checkFechaDeNacimiento;
				
				if(bandera && checkEmail && checkFechaDeNacimiento&& agregando) {
					PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText(),ventanaPersona.getTxtCalle().getText(),ventanaPersona.getTxtAltura().getText(),ventanaPersona.getTxtPiso().getText(),ventanaPersona.getTxtDepto().getText(),this.localidades.obtenerId(ventanaPersona.getTxtLocalidad().getSelectedItem().toString()),ventanaPersona.getTxtEmail().getText(),ventanaPersona.getTxtFechaDeCumpleaños().getText(),this.contactos.obtenerId(ventanaPersona.getTxtTipoDeContacto().getSelectedItem().toString()) );
					this.agenda.agregarPersona(nuevaPersona);
					this.llenarTabla();
					this.ventanaPersona.dispose();
				}
				
			}
			
			else if(e.getSource() == this.ventanaPersona.getBtnEditarPersona()) {
				
				boolean checkEmail = true;
				boolean checkFechaDeNacimiento = true;
				
				String email = this.ventanaPersona.getTxtEmail().getText();
				String fechaDeNacimiento = this.ventanaPersona.getTxtFechaDeCumpleaños().getText();
				
				if(!email.isEmpty()) {
					checkEmail = verificarEmail();
				}
				
				if(!fechaDeNacimiento.isEmpty()) {
					checkFechaDeNacimiento = verificarFechaNacimiento();
				}
				
				boolean bandera = verificarNombreCalle() && checkEmail && checkFechaDeNacimiento;
				
				if(bandera && !agregando) {
					PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText(),ventanaPersona.getTxtCalle().getText(),ventanaPersona.getTxtAltura().getText(),ventanaPersona.getTxtPiso().getText(),ventanaPersona.getTxtDepto().getText(),this.localidades.obtenerId(ventanaPersona.getTxtLocalidad().getSelectedItem().toString()),ventanaPersona.getTxtEmail().getText(),ventanaPersona.getTxtFechaDeCumpleaños().getText(),this.contactos.obtenerId(ventanaPersona.getTxtTipoDeContacto().getSelectedItem().toString()));
//					System.out.println(this.ventanaPersona.getIdEditar());
					this.agenda.editarPersona(nuevaPersona, this.ventanaPersona.getIdEditar());
					this.llenarTabla();
					this.ventanaPersona.dispose();
				}
			}
			
		}
		
		private boolean verificarEmail() {
			if(VerificadorDeDatos.verificarEmail(this.ventanaPersona.getTxtEmail().getText())) {
				return true;
			}else {
				this.ventanaPersona.notificarEmailErroneo();
				return false;
			}
		}
		
		private boolean verificarFechaNacimiento() {
			if(VerificadorDeDatos.verificarFecha(this.ventanaPersona.getTxtFechaDeCumpleaños().getText())) {
				return true;
			}else {
				this.ventanaPersona.notificarFechaDeNacimientoErronea();
				return false;
			}
		}

		private boolean verificarNombreCalle() {
			boolean bandera = true;
			
			JTextField nombre = this.ventanaPersona.getTxtNombre();
			JTextField calle = this.ventanaPersona.getTxtCalle();
			
			if(nombre.getText().isEmpty() || calle.getText().isEmpty()) {
				this.ventanaPersona.notificarCamposRequeridos();
				bandera = false;
			}
			
			return bandera;
		}

		private int[] obtenerFila() {
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			return filas_seleccionadas;
		}
}
