package presentacion.controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.ContactoDTO;
import presentacion.vista.VentanaContacto;

public class ControladorTipoContacto implements ActionListener {
	VentanaContacto ventanaContacto;
	
	public ControladorTipoContacto(VentanaContacto v) {
		this.ventanaContacto = v;
	}
	
	public ControladorTipoContacto() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ventanaContacto.getBtnAgregar()) {
			ventanaContacto.visibleAceptar();
		}
		
		else if(e.getSource() == ventanaContacto.getBtnBorrar()) {
			int[] filas = ventanaContacto.getTablaContactos().getSelectedRows();
			
			if(filas.length == 0) {
				JOptionPane.showMessageDialog(null, "Seleccione un contacto para borrar");
			} else {
				for (int fila:filas)
				{
					ventanaContacto.getControlador().getContactos().borrarContacto(ventanaContacto.getControlador().getContactos_en_tabla().get(fila));
				}
				ventanaContacto.getControlador().llenarTablaContacto();
			}
		}
		
		else if(e.getSource() == ventanaContacto.getBtnEditar()) {
			int[] filas = ventanaContacto.getTablaContactos().getSelectedRows();
			
			if(filas.length == 0) {
				JOptionPane.showMessageDialog(null, "Seleccione un contacto para editar");
			} else {
				for (int fila:filas)
				{
					ContactoDTO contact = ventanaContacto.getControlador().getContactos().obtenerContacto(ventanaContacto.getControlador().getContactos_en_tabla().get(fila).getIdContacto());
				
					ventanaContacto.idEditar = contact.getIdContacto();
					ventanaContacto.getTxtAgreg().setText(contact.getNombre());
				}
				
				ventanaContacto.getControlador().llenarTablaContacto();
				ventanaContacto.visibleAceptarEdicion();
			}
		}
		
		else if(e.getSource() == ventanaContacto.getBtnAceptarEdicion()) {
			ContactoDTO contactoEditar=new ContactoDTO(0,ventanaContacto.getTxtAgreg().getText());
			
			ventanaContacto.getControlador().getContactos().editar(contactoEditar, ventanaContacto.getIdEditar());
			ventanaContacto.getControlador().llenarTablaContacto();
			ventanaContacto.getTxtAgreg().setText("");
		}
		
		else if(e.getSource() == ventanaContacto.getBtnAceptar()) {
			String nombreContactoAgregar = ventanaContacto.getTxtAgreg().getText();
			
			if(nombreContactoAgregar.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor complete un tipo de contacto");
			} else {
				if(ventanaContacto.getControlador().getContactos().obtenerId(nombreContactoAgregar) == 0 && !nombreContactoAgregar.equals("")) {
					ContactoDTO nuevoContacto = new ContactoDTO(0,nombreContactoAgregar);
					ventanaContacto.getControlador().getContactos().agregarContacto(nuevoContacto);
					ventanaContacto.getTxtAgreg().setText("");
					ventanaContacto.getControlador().llenarTablaContacto();
				}
			}
		}
	}
	
	public VentanaContacto getV() {
		return ventanaContacto;
	}

	public void setV(VentanaContacto v) {
		this.ventanaContacto = v;
	}
}
