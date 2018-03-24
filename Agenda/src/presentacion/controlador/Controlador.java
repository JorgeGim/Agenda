package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import modelo.Agenda;
import modelo.Localidades;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private List<LocalidadDTO> localidades_en_tabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		private Localidades localidades;
		private ArrayList<Integer> indices;
		private VentanaLocalidad ventanaLocalidad;
		private boolean agregando;
		
		public Controlador(Vista vista, Agenda agenda,Localidades localidad)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);
			this.vista.getBtnLocalidades().addActionListener(this);
			this.agenda = agenda;
			this.localidades=localidad;
			this.personas_en_tabla = null;
			this.localidades_en_tabla=null;
			this.indices = new ArrayList<Integer>();
		}
		
		public void inicializar()
		{
			this.llenarTabla();
			this.vista.show();
			this.ventanaLocalidad = new VentanaLocalidad(this);
		}
		
		private void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				String local = this.localidades.obtenerLocalidad(this.personas_en_tabla.get(i).getLocalidad()).getNombre();
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(),this.personas_en_tabla.get(i).getCalle(),this.personas_en_tabla.get(i).getAltura(),this.personas_en_tabla.get(i).getPiso(),this.personas_en_tabla.get(i).getDepto(),local,this.personas_en_tabla.get(i).getEmail(),this.personas_en_tabla.get(i).getFechaCumplea�os(),this.personas_en_tabla.get(i).getTipoContacto()};
				this.vista.getModelPersonas().addRow(fila);
			}			
		}
		
		private void llenarTablaLocalidad() {
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
		
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.vista.getBtnAgregar())
			{
				this.ventanaPersona = new VentanaPersona(this,this.localidades);
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
			{				
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
				reporte.mostrar();				
			}
			else if(e.getSource() == this.vista.getBtnEditar())
			{
				int[] filas_seleccionadas = obtenerFila();
				for (int fila:filas_seleccionadas)
				{
					indices.clear();
					indices.add(fila);
					this.ventanaPersona = new VentanaPersona(this,this.localidades);
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
					this.ventanaPersona.setTxtFechaDeCumplea�os(persona.getFechaCumplea�os().toString());
					this.ventanaPersona.setTxtTipoDeContacto(persona.getTipoContacto());
					
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

				this.ventanaLocalidad.getBtnAgregar().addActionListener(this);
				this.ventanaLocalidad.getBtnBorrar().addActionListener(this);
				this.ventanaLocalidad.getBtnEditar().addActionListener(this);
				this.ventanaLocalidad.getBtnAceptar().addActionListener(this);
				this.ventanaLocalidad.getBtnAceptarEdicion().addActionListener(this);
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnAgregar()) {
				System.out.println("agregando");
				ventanaLocalidad.visibleAceptar();
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnBorrar()) {
				int[] filas = this.ventanaLocalidad.getTablaLocalidades().getSelectedRows();
				for (int fila:filas)
				{
					this.localidades.borrarLocalidad(this.localidades_en_tabla.get(fila));
				}
				//actualiza la tabla
				this.llenarTablaLocalidad();
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnEditar()) {
				System.out.println("editando");
				int[] filas = this.ventanaLocalidad.getTablaLocalidades().getSelectedRows();
				for (int fila:filas)
				{
					LocalidadDTO local = this.localidades.obtenerLocalidad(this.localidades_en_tabla.get(fila).getIdLocalidad());
					
					this.ventanaLocalidad.setIdEditar(local.getIdLocalidad());
					this.ventanaLocalidad.setTxtAgreg(local.getNombre());
					System.out.println(local.getNombre());
				}
				//actualiza la tabla
				this.llenarTablaLocalidad();
				ventanaLocalidad.visibleAceptarEdicion();
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnAceptarEdicion()) {
				
				LocalidadDTO localidadEditar=new LocalidadDTO(0,this.ventanaLocalidad.getTxtAgreg().getText());
				this.localidades.editar(localidadEditar, this.ventanaLocalidad.getIdEditar());
				this.llenarTablaLocalidad();
				//this.ventanaLocalidad.dispose();
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnAceptar()) {
				String nombreLocalidadAgregar = this.ventanaLocalidad.getTxtAgreg().getText();
				if(localidades.obtenerId(nombreLocalidadAgregar) == 0 && !nombreLocalidadAgregar.equals("")) {
				LocalidadDTO nuevaLocalidad = new LocalidadDTO(0,nombreLocalidadAgregar);
				this.localidades.agregarLocalidad(nuevaLocalidad);
				this.ventanaLocalidad.setTxtAgreg("");
				this.llenarTablaLocalidad();
				}
			}
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())
			{
				
				/*for (Integer fila:indices)
				{
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}
				indices.clear();
				*/
				boolean bandera = verificarNombreCalle();
				
				if(bandera && agregando) {
					//ventanaPersona.getTxtLocalidad().getSelectedItem().toString()
					PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText(),ventanaPersona.getTxtCalle().getText(),ventanaPersona.getTxtAltura().getText(),ventanaPersona.getTxtPiso().getText(),ventanaPersona.getTxtDepto().getText(),this.localidades.obtenerId(ventanaPersona.getTxtLocalidad().getSelectedItem().toString()),ventanaPersona.getTxtEmail().getText(),ventanaPersona.getTxtFechaDeCumplea�os().getText(),ventanaPersona.getTxtTipoDeContacto().getSelectedItem().toString());
					this.agenda.agregarPersona(nuevaPersona);
					this.llenarTabla();
					this.ventanaPersona.dispose();
				}
				
			}
			else if(e.getSource() == this.ventanaPersona.getBtnEditarPersona()) {
				boolean bandera= verificarNombreCalle();
				
				if(bandera && !agregando) {
					PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText(),ventanaPersona.getTxtCalle().getText(),ventanaPersona.getTxtAltura().getText(),ventanaPersona.getTxtPiso().getText(),ventanaPersona.getTxtDepto().getText(),this.localidades.obtenerId(ventanaPersona.getTxtLocalidad().getSelectedItem().toString()),ventanaPersona.getTxtEmail().getText(),ventanaPersona.getTxtFechaDeCumplea�os().getText(),ventanaPersona.getTxtTipoDeContacto().getSelectedItem().toString());
//					System.out.println(this.ventanaPersona.getIdEditar());
					this.agenda.editarPersona(nuevaPersona, this.ventanaPersona.getIdEditar());
					this.llenarTabla();
					this.ventanaPersona.dispose();
				}
			}
			
		}

		private boolean verificarNombreCalle() {
			boolean bandera = true;
			
			JTextField nombre = this.ventanaPersona.getTxtNombre();
			JTextField calle = this.ventanaPersona.getTxtCalle();
			
			if(nombre.getText().isEmpty()) {
				this.ventanaPersona.notificarCamposRequeridos();
				bandera = false;
			}
			
			if(calle.getText().isEmpty()) {
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
