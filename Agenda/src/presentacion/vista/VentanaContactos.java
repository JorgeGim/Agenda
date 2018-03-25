package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.ContactoDTO;
import dto.LocalidadDTO;
import presentacion.controlador.Controlador;

public class VentanaContactos extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private JFrame frame;
	private JPanel panel;
	private DefaultTableModel modelContactos;
	private String [] nombreColumna= {"Tipo de contacto"};
	private JTable tablaContactos;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnAceptar;
	private JButton btnAceptarEdicion;
	private JTextField txtAgreg;
	private int idEditar;
	
	public VentanaContactos(Controlador controlador){
		super();
		this.controlador = controlador;
		initialize();
	}

	public int getIdEditar() {
		return idEditar;
	}

	public void setIdEditar(int idEditar) {
		this.idEditar = idEditar;
	}

	private void initialize() {
		
		crearVentana();
		
		crearComponentes();
	}

	private void crearVentana() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(1000, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 307, 123);
		panel.setSize(1000, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 950, 200);
		panel.add(spLocalidades);
		
		
		modelContactos = new DefaultTableModel(null,nombreColumna);
		tablaContactos = new JTable(modelContactos);
		
		tablaContactos.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaContactos.getColumnModel().getColumn(0).setResizable(false);

		spLocalidades.setViewportView(tablaContactos);
	}

	public DefaultTableModel getModelContactos() {
		return modelContactos;
	}

	public void setModelContactos(DefaultTableModel modelContactos) {
		this.modelContactos = modelContactos;
	}

	public JTable getTablaContactos() {
		return tablaContactos;
	}

	public void setTablaContactos(JTable tablaContactos) {
		this.tablaContactos = tablaContactos;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnAceptarEdicion() {
		return btnAceptarEdicion;
	}

	public void setBtnAceptarEdicion(JButton btnAceptarEdicion) {
		this.btnAceptarEdicion = btnAceptarEdicion;
	}

	public JTextField getTxtAgreg() {
		return txtAgreg;
	}

	public void setTxtAgreg(JTextField txtAgreg) {
		this.txtAgreg = txtAgreg;
	}

	private void crearComponentes() {
		btnAgregar= new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10,328,89,23);
		panel.add(btnAceptar);
		btnAceptar.setVisible(false);
		
		btnAceptarEdicion = new JButton("Editar");
		btnAceptarEdicion.setBounds(10,328,89,23);
		panel.add(btnAceptarEdicion);
		btnAceptarEdicion.setVisible(false);
		
		txtAgreg = new JTextField();
		txtAgreg.setBounds(109,328 , 89, 23);
		panel.add(txtAgreg);
		txtAgreg.setVisible(false);
			

		this.getBtnAgregar().addActionListener(this);
		this.getBtnBorrar().addActionListener(this);
		this.getBtnEditar().addActionListener(this);
		this.getBtnAceptar().addActionListener(this);
		this.getBtnAceptarEdicion().addActionListener(this);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setVisible(true);
	}
	public void visibleAceptar() {
		btnAceptar.setVisible(true);
		txtAgreg.setVisible(true);
		btnAceptarEdicion.setVisible(false);
	}
	public void visibleAceptarEdicion() {
		btnAceptarEdicion.setVisible(true);
		txtAgreg.setVisible(true);
		btnAceptar.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.getBtnAgregar()) {
			System.out.println("agregando");
			visibleAceptar();
		}
		else if(e.getSource() == this.getBtnBorrar()) {
			int[] filas = this.getTablaContactos().getSelectedRows();
			for (int fila:filas)
			{
				controlador.getContactos().borrarContacto(controlador.getContactos_en_tabla().get(fila));
			}
			//actualiza la tabla
			controlador.llenarTablaContacto();
		}
		else if(e.getSource() == this.getBtnEditar()) {
			System.out.println("editando");
			int[] filas = this.getTablaContactos().getSelectedRows();
			for (int fila:filas)
			{
				ContactoDTO contact = controlador.getContactos().obtenerContacto(controlador.getContactos_en_tabla().get(fila).getIdContacto());
				
				idEditar=contact.getIdContacto();
				this.txtAgreg.setText(contact.getNombre());
				System.out.println(contact.getNombre());
			}
			//actualiza la tabla
			controlador.llenarTablaContacto();
			visibleAceptarEdicion();
		}
		else if(e.getSource() == this.getBtnAceptarEdicion()) {
			
			ContactoDTO contactoEditar=new ContactoDTO(0,this.getTxtAgreg().getText());
			controlador.getContactos().editar(contactoEditar, this.getIdEditar());
			controlador.llenarTablaContacto();
			this.txtAgreg.setText("");
			//this.ventanaLocalidad.dispose();
		}
		else if(e.getSource() == this.getBtnAceptar()) {
			String nombreContactoAgregar = this.txtAgreg.getText();
			if(controlador.getContactos().obtenerId(nombreContactoAgregar) == 0 && !nombreContactoAgregar.equals("")) {
			ContactoDTO nuevoContacto = new ContactoDTO(0,nombreContactoAgregar);
			controlador.getContactos().agregarContacto(nuevoContacto);
			this.txtAgreg.setText("");
			controlador.llenarTablaContacto();
			}
		}

	}

	public DefaultTableModel getModelPersonas() {
		return modelContactos;
	}

	public String[] getNombreColumna() {
		return nombreColumna;
	}

}
