package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorLocalidad;

public class VentanaLocalidad extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	private ControladorLocalidad controladorLocalidad;
	private JPanel contentPane;
	private JPanel panel;
	private JTable tablaLocalidades;
	private  String[] nombreColumna = {"Nombre localidades"};
	private DefaultTableModel modelLocalidades;
	private JFrame frame;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnAceptar;
	private JButton btnAceptarEdicion;
	private JTextField txtAgreg;
	private int idEditar;
	private JButton btnAtras;

	public VentanaLocalidad(Controlador controlador, ControladorLocalidad cl) {
		
		super();
		this.controlador = controlador;
		this.controladorLocalidad = cl;
		initialize();
	}
	
	public String[] getNombreColumna() {
		return nombreColumna;
	}

	public DefaultTableModel getModelPersonas() {
		return modelLocalidades;
	}

	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}

	private void initialize(){
		
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
		
		
		modelLocalidades = new DefaultTableModel(null,nombreColumna);
		tablaLocalidades = new JTable(modelLocalidades);
		
		tablaLocalidades.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidades.getColumnModel().getColumn(0).setResizable(false);

		spLocalidades.setViewportView(tablaLocalidades);
		
		crearComponentes();
	}

	private void crearComponentes() {
		btnAgregar = new JButton("Agregar");
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
		
		btnAtras= new JButton("Atras");
		btnAtras.setBounds(400, 328, 89, 23);
		panel.add(btnAtras);
		
		this.getBtnAgregar().addActionListener(controladorLocalidad);
		this.getBtnBorrar().addActionListener(controladorLocalidad);
		this.getBtnEditar().addActionListener(controladorLocalidad);
		this.getBtnAceptar().addActionListener(controladorLocalidad);
		this.getBtnAceptarEdicion().addActionListener(controladorLocalidad);
		this.getBtnAtras().addActionListener(controladorLocalidad);
		
	}
	
	public JTextField getTxtAgreg() {
		return txtAgreg;
	}
	
	public void setTxtAgreg(String txtAgreg) {
		this.txtAgreg.setText(txtAgreg);
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnAceptarEdicion() {
		return btnAceptarEdicion;
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
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	public void setIdEditar(int idEditar) {
		this.idEditar=idEditar;
	}
	
	public int getIdEditar() {
		return idEditar;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.getBtnAgregar()) {
			System.out.println("agregando");
			visibleAceptar();
		}
		else if(e.getSource() == this.getBtnBorrar()) {
			int[] filas = this.getTablaLocalidades().getSelectedRows();
			for (int fila:filas)
			{
				controlador.getLocalidades().borrarLocalidad(controlador.getLocalidades_en_tabla().get(fila));
			}
			//actualiza la tabla
			controlador.llenarTablaLocalidad();
		}
		else if(e.getSource() == this.getBtnEditar()) {
			System.out.println("editando");
			int[] filas = this.getTablaLocalidades().getSelectedRows();
			for (int fila:filas)
			{
				LocalidadDTO local = controlador.getLocalidades().obtenerLocalidad(controlador.getLocalidades_en_tabla().get(fila).getIdLocalidad());
				
				this.setIdEditar(local.getIdLocalidad());
				this.setTxtAgreg(local.getNombre());
				System.out.println(local.getNombre());
			}
			//actualiza la tabla
			controlador.llenarTablaLocalidad();
			visibleAceptarEdicion();
		}
		else if(e.getSource() == this.getBtnAceptarEdicion()) {
			
			LocalidadDTO localidadEditar=new LocalidadDTO(0,this.getTxtAgreg().getText());
			controlador.getLocalidades().editar(localidadEditar, this.getIdEditar());
			controlador.llenarTablaLocalidad();
			this.setTxtAgreg("");
			//this.ventanaLocalidad.dispose();
		}
		else if(e.getSource() == this.getBtnAceptar()) {
			String nombreLocalidadAgregar = this.getTxtAgreg().getText();
			if(controlador.getLocalidades().obtenerId(nombreLocalidadAgregar) == 0 && !nombreLocalidadAgregar.equals("")) {
			LocalidadDTO nuevaLocalidad = new LocalidadDTO(0,nombreLocalidadAgregar);
			controlador.getLocalidades().agregarLocalidad(nuevaLocalidad);
			this.setTxtAgreg("");
			controlador.llenarTablaLocalidad();
			}
		}
		else if(e.getSource() == this.getBtnAtras()) {
			System.out.println("atras");
			
		}
		
	}
}
