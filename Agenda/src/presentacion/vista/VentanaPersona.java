package presentacion.vista;


import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCalle;
	private JTextField txtEmail;
	private JTextField txtFechaDeCumpleaños;
	private JComboBox txtTipoDeContacto;
	private JButton btnAgregarPersona;
	private JButton btnEditarPersona;
	private Controlador controlador;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private JComboBox txtLocalidad;
	private Label notificadorCamposObligatorios;
	private JPanel panel;

	public VentanaPersona(Controlador controlador) 
	{
		super();
		this.controlador = controlador;
		
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
		
		JLabel lblFechaCumpleaños = new JLabel("Fecha cumpleaños");
		lblFechaCumpleaños.setBounds(10, 216, 113, 14);
		panel.add(lblFechaCumpleaños);
		
		Label lblFormatoFechaCumpleaños = new Label("dd/mm/yyyy");
		lblFormatoFechaCumpleaños.setBounds(310, 215, 113, 14);
		lblFormatoFechaCumpleaños.setForeground(Color.red);
		panel.add(lblFormatoFechaCumpleaños);
		
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

		txtLocalidad = new JComboBox();
		txtLocalidad.setBounds(433, 131, 164, 20); // (133,y+41,164,20)
		panel.add(txtLocalidad);
		txtLocalidad.addItem("Trujui");
		txtLocalidad.addItem("Malvinas Argentinas");
		txtLocalidad.addItem("San Miguel Arcangel");

		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 172, 164, 20); // (133,y+41,164,20)
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtFechaDeCumpleaños = new JTextField();
		txtFechaDeCumpleaños.setBounds(133, 213, 164, 20); // (133,y+41,164,20)
		panel.add(txtFechaDeCumpleaños);
		txtFechaDeCumpleaños.setColumns(10);
		
		txtTipoDeContacto = new JComboBox();
		txtTipoDeContacto.setBounds(133, 254, 164, 20); // (133,y+41,164,20)
		txtTipoDeContacto.addItem("Trabajo");
		txtTipoDeContacto.addItem("Familia");
		txtTipoDeContacto.addItem("Amigos");
		txtTipoDeContacto.addItem("Otros");
		panel.add(txtTipoDeContacto);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(208, 291, 89, 23);
		panel.add(btnAgregarPersona);
		
		this.setVisible(true);
	}
	
	public void obtenerPersona(String nombre) {
		
	}
	
	public void notificarCamposRequeridos() {
		notificadorCamposObligatorios = new Label("ERROR: Complete NOMBRE y CALLE");
		notificadorCamposObligatorios.setBounds(310, 295, 230, 14);
		notificadorCamposObligatorios.setForeground(Color.RED);
		panel.add(notificadorCamposObligatorios);
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

	public void setTxtFechaDeCumpleaños(String txtFechaDeCumpleaños) {
		this.txtFechaDeCumpleaños.setText(txtFechaDeCumpleaños);
	}

	public void setTxtTipoDeContacto(int i) {
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

	public void setTxtLocalidad(int i) {
		this.txtLocalidad.setSelectedItem(i);
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

	public JTextField getTxtFechaDeCumpleaños() {
		return txtFechaDeCumpleaños;
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
		return txtLocalidad;
	}
	
}

