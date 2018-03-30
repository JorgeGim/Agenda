package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dto.LocalidadDTO;
import presentacion.vista.VentanaLocalidad;

public class ControladorLocalidad implements ActionListener {
	
	VentanaLocalidad ventanaLocalidad;
	
	public ControladorLocalidad() {
		
	}
	
	public ControladorLocalidad(VentanaLocalidad ventanaLocalidad) {
		this.ventanaLocalidad = ventanaLocalidad;
	}
	
	public VentanaLocalidad getVentanaLocalidad() {
		return ventanaLocalidad;
	}

	public void setVentanaLocalidad(VentanaLocalidad ventanaLocalidad) {
		this.ventanaLocalidad = ventanaLocalidad;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ventanaLocalidad.getBtnAgregar()) {
			System.out.println("agregando");
			ventanaLocalidad.visibleAceptar();
		}
		
		else if(e.getSource() == ventanaLocalidad.getBtnBorrar()) {
			int[] filas = ventanaLocalidad.getTablaLocalidades().getSelectedRows();
			for (int fila:filas)
			{
				ventanaLocalidad.getControlador().getLocalidades().borrarLocalidad(ventanaLocalidad.getControlador().getLocalidades_en_tabla().get(fila));
			}
			//actualiza la tabla
			ventanaLocalidad.getControlador().llenarTablaLocalidad();
		}
		
		else if(e.getSource() == ventanaLocalidad.getBtnEditar()) {
			System.out.println("editando");
			int[] filas = ventanaLocalidad.getTablaLocalidades().getSelectedRows();
			for (int fila:filas)
			{
				LocalidadDTO local = ventanaLocalidad.getControlador().getLocalidades().obtenerLocalidad(ventanaLocalidad.getControlador().getLocalidades_en_tabla().get(fila).getIdLocalidad());
				
				ventanaLocalidad.setIdEditar(local.getIdLocalidad());
				ventanaLocalidad.setTxtAgreg(local.getNombre());
				System.out.println(local.getNombre());
			}
			//actualiza la tabla
			ventanaLocalidad.getControlador().llenarTablaLocalidad();
			ventanaLocalidad.visibleAceptarEdicion();
		}
		
		else if(e.getSource() == ventanaLocalidad.getBtnAceptarEdicion()) {
			
			LocalidadDTO localidadEditar=new LocalidadDTO(0, ventanaLocalidad.getTxtAgreg().getText());
			ventanaLocalidad.getControlador().getLocalidades().editar(localidadEditar, ventanaLocalidad.getIdEditar());
			ventanaLocalidad.getControlador().llenarTablaLocalidad();
			ventanaLocalidad.setTxtAgreg("");
			//this.ventanaLocalidad.dispose();
		}
		
		else if(e.getSource() == ventanaLocalidad.getBtnAceptar()) {
			String nombreLocalidadAgregar = ventanaLocalidad.getTxtAgreg().getText();
			
			if(ventanaLocalidad.getControlador().getLocalidades().obtenerId(nombreLocalidadAgregar) == 0 && !nombreLocalidadAgregar.equals("")) {
				LocalidadDTO nuevaLocalidad = new LocalidadDTO(0,nombreLocalidadAgregar);
				ventanaLocalidad.getControlador().getLocalidades().agregarLocalidad(nuevaLocalidad);
				ventanaLocalidad.setTxtAgreg("");
				ventanaLocalidad.getControlador().llenarTablaLocalidad();
			}
		}
		
		else if(e.getSource() == ventanaLocalidad.getBtnAtras()) {
			System.out.println("atras");
			
		}
		
	}
		
}


