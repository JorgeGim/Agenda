package persistencia.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import persistencia.conexion.Conexion;

public class VentanaConexion extends JFrame implements ActionListener{

	private JPanel panel;
	private JButton btnAgregar;
	private JTextField ip;
	private JTextField puerto;
	private JTextField usuario;
	private JTextField contraseņa;
	private	JLabel lblIp;
	private JLabel lblPuerto;
	private JLabel lblUsuario;
	private JLabel lblContraseņa;
	
	public VentanaConexion() {
		super();
		initialize();
		
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		
		panel = new JPanel();
		//panel.setBounds(10, 11, 307, 123);
		panel.setSize(1000, 400);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		
		crearComponentes();
		

	}

	private void crearComponentes() {
		lblIp= new JLabel("IP");
		lblPuerto= new JLabel("Puerto");
		lblUsuario= new JLabel("Usuario");
		lblContraseņa= new JLabel("Contraseņa");
		
		lblIp.setBounds(10, 11, 113, 14);
		lblPuerto.setBounds(10, 52, 113, 14);
		lblUsuario.setBounds(10, 93, 113, 14);
		lblContraseņa.setBounds(10, 134, 113, 14);
		
		
		panel.add(lblIp);
		panel.add(lblPuerto);
		panel.add(lblUsuario);
		panel.add(lblContraseņa);

		
		ip=new JTextField("127.0.0.1");
		puerto=new JTextField("3306");
		usuario=new JTextField("root");
		contraseņa=new JTextField("pass");
		ip.setVisible(true);
		
		ip.setBounds(133, 8, 164, 20);
		puerto.setBounds(133, 49, 164, 20);
		usuario.setBounds(133, 90, 164, 20);
		contraseņa.setBounds(133, 131, 164, 20);
		
		panel.add(ip);
		panel.add(puerto);
		panel.add(usuario);
		panel.add(contraseņa);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(10, 291, 89, 23);
		panel.add(btnAgregar);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
}
