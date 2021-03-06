package presentacion.vista;


import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.ContactoDTO;
import dto.LocalidadDTO;
import modelo.Contacto;
import modelo.Localidad;
import presentacion.controlador.Controlador;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCalle;
	private JTextField txtEmail;
	private JTextField txtFechaDeCumplea�os;
	private JComboBox txtTipoDeContacto;
	private JButton btnAgregarPersona;
	private JButton btnEditarPersona;
	private Controlador controlador;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private JComboBox comboBoxLocalidad;
	private JPanel panel;
	private Localidad localidades;
	private Contacto contactos;
	private int idEditar;

	public VentanaPersona(Controlador controlador, Localidad localidades, Contacto contactos) 
	{
		super();
		this.controlador = controlador;
		this.localidades=localidades;
		this.contactos=contactos;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(200, 200, 343, 183);
		setBounds(100, 100, 450, 300);
		setSize(1000,400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		//panel.setBounds(10, 11, 307, 123);
		panel.setSize(1000, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 93, 113, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(310, 93, 113, 14); //(x+300,93,113,14)
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(610, 93, 113, 14);
		panel.add(lblPiso);

		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(10, 134, 113, 14);
		panel.add(lblDepto);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(310, 134, 113, 14);
		panel.add(lblLocalidad);

		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 175, 113, 14);
		panel.add(lblEmail);
		
		JLabel lblFechaCumplea�os = new JLabel("Fecha cumplea�os");
		lblFechaCumplea�os.setBounds(10, 216, 113, 14);
		panel.add(lblFechaCumplea�os);
		
		Label lblFormatoFechaCumplea�os = new Label("dd/mm/yyyy");
		lblFormatoFechaCumplea�os.setBounds(310, 215, 113, 14);
		lblFormatoFechaCumplea�os.setForeground(Color.red);
		panel.add(lblFormatoFechaCumplea�os);
		
		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(10, 257, 113, 14);
		panel.add(lblTipoDeContacto);
		
		//--
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(133, 90, 164, 20); // (133,y+41,164,20)
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		txtAltura = new JTextField();
		txtAltura.setBounds(433, 90, 164, 20); // (133,y+41,164,20)
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		txtPiso = new JTextField();
		txtPiso.setBounds(733, 90, 164, 20); // (133,y+41,164,20)
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepto = new JTextField();
		txtDepto.setBounds(133, 131, 164, 20); // (133,y+41,164,20)
		panel.add(txtDepto);
		txtDepto.setColumns(10);

		comboBoxLocalidad = new JComboBox();
		comboBoxLocalidad.setBounds(433, 131, 164, 20); // (133,y+41,164,20)
		panel.add(comboBoxLocalidad);
		List<LocalidadDTO> lista = this.localidades.obtenerLocalidades();
		
		for(LocalidadDTO localidad :lista) {
			comboBoxLocalidad.addItem(localidad.toString());
		}
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 172, 164, 20); // (133,y+41,164,20)
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtFechaDeCumplea�os = new JTextField();
		txtFechaDeCumplea�os.setBounds(133, 213, 164, 20); // (133,y+41,164,20)
		panel.add(txtFechaDeCumplea�os);
		txtFechaDeCumplea�os.setColumns(10);
		
		txtTipoDeContacto = new JComboBox();
		txtTipoDeContacto.setBounds(133, 254, 164, 20); // (133,y+41,164,20)
		panel.add(txtTipoDeContacto);
		List<ContactoDTO> listaContactos= this.contactos.obtenerContactos();
		
		for(ContactoDTO contacto: listaContactos) {
		 txtTipoDeContacto.addItem(contacto.getNombre());
		}
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(10, 291, 89, 23);
		panel.add(btnAgregarPersona);
		
		btnEditarPersona = new JButton("Editar");
		btnEditarPersona.addActionListener(this.controlador);
		btnEditarPersona.setBounds(208, 291, 89, 23);
		panel.add(btnEditarPersona);
		
		this.setVisible(true);
	}
	
	public JButton getBtnEditarPersona() {
		return btnEditarPersona;
	}

	public void obtenerPersona(String nombre) {
		
	}
	
	public void notificarCamposRequeridos() {
		JOptionPane.showMessageDialog(null, "Complete NOMBRE y CALLE");
	}
	
	public void notificarEmailErroneo() {
		JOptionPane.showMessageDialog(null, "Formato de email no v�lido");
	}
	
	public void notificarFechaDeNacimientoErronea() {
		JOptionPane.showMessageDialog(null, "Formato de fecha no v�lida");
	}
	
	public void setTxtNombre(String txtNombre) {
		this.txtNombre.setText(txtNombre);
	}

	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono.setText(txtTelefono);
	}

	public void setTxtCalle(String txtCalle) {
		this.txtCalle.setText(txtCalle);
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail.setText(txtEmail);
	}

	public void setTxtFechaDeCumplea�os(String txtFechaDeCumplea�os) {
		this.txtFechaDeCumplea�os.setText(txtFechaDeCumplea�os);
	}

	public void setTxtTipoDeContacto(String i) {
		this.txtTipoDeContacto.setSelectedItem(i);
	}

	public void setTxtAltura(String txtAltura) {
		this.txtAltura.setText(txtAltura);
	}

	public void setTxtPiso(String txtPiso) {
		this.txtPiso.setText(txtPiso);
	}

	public void setTxtDepto(String txtDepto) {
		this.txtDepto.setText(txtDepto);
	}

	public void setTxtLocalidad(String nombre) {
		this.comboBoxLocalidad.setSelectedItem(nombre);
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtFechaDeCumplea�os() {
		return txtFechaDeCumplea�os;
	}

	public JComboBox getTxtTipoDeContacto() {
		return txtTipoDeContacto;
	}

	public JTextField getTxtAltura() {
		return txtAltura;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public JTextField getTxtDepto() {
		return txtDepto;
	}

	public JComboBox getTxtLocalidad() {
		return comboBoxLocalidad;
	}

	public void setIdPersonaAEditar(int idPersona) {
		this.idEditar=idPersona;
	}
	
	public int getIdEditar() {
		return idEditar;
	}
	
}

