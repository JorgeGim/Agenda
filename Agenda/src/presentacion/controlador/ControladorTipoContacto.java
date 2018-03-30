package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dto.ContactoDTO;
import dto.LocalidadDTO;
import presentacion.vista.VentanaContacto;

public class ControladorTipoContacto implements ActionListener {
	
	VentanaContacto v;
	
	public VentanaContacto getV() {
		return v;
	}

	public void setV(VentanaContacto v) {
		this.v = v;
	}

	public ControladorTipoContacto(VentanaContacto v) {
		this.v = v;
	}
	
	public ControladorTipoContacto() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == v.getBtnAgregar()) {
			System.out.println("agregando");
			v.visibleAceptar();
		}
		else if(e.getSource() == v.getBtnBorrar()) {
			int[] filas = v.getTablaContactos().getSelectedRows();
			for (int fila:filas)
			{
				v.getControlador().getContactos().borrarContacto(v.getControlador().getContactos_en_tabla().get(fila));
			}
			//actualiza la tabla
			v.getControlador().llenarTablaContacto();
		}
		else if(e.getSource() == v.getBtnEditar()) {
			System.out.println("editando");
			int[] filas = v.getTablaContactos().getSelectedRows();
			for (int fila:filas)
			{
				ContactoDTO contact = v.getControlador().getContactos().obtenerContacto(v.getControlador().getContactos_en_tabla().get(fila).getIdContacto());
				
				
				v.idEditar = contact.getIdContacto();
				v.getTxtAgreg().setText(contact.getNombre());
				System.out.println(contact.getNombre());
			}
			//actualiza la tabla
			v.getControlador().llenarTablaContacto();
			v.visibleAceptarEdicion();
		}
		else if(e.getSource() == v.getBtnAceptarEdicion()) {
			
			ContactoDTO contactoEditar=new ContactoDTO(0,v.getTxtAgreg().getText());
			v.getControlador().getContactos().editar(contactoEditar, v.getIdEditar());
			v.getControlador().llenarTablaContacto();
			v.getTxtAgreg().setText("");
			//this.ventanaLocalidad.dispose();
		}
		else if(e.getSource() == v.getBtnAceptar()) {
			String nombreContactoAgregar = v.getTxtAgreg().getText();
			if(v.getControlador().getContactos().obtenerId(nombreContactoAgregar) == 0 && !nombreContactoAgregar.equals("")) {
			ContactoDTO nuevoContacto = new ContactoDTO(0,nombreContactoAgregar);
			v.getControlador().getContactos().agregarContacto(nuevoContacto);
			v.getTxtAgreg().setText("");
			v.getControlador().llenarTablaContacto();
			}
		}

	}

}
