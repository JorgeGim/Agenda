package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;
import modelo.Conexiones;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;

public class VentanaConexion extends JFrame implements ActionListener{

	private JPanel panel;
	private JButton btnAgregar;
	private JTextField ip;
	private JTextField puerto;
	private JTextField usuario;
	private JTextField contraseña;
	private	JLabel lblIp;
	private JLabel lblPuerto;
	private JLabel lblUsuario;
	private JLabel lblContraseña;
	private Conexiones conexion;
	
	public VentanaConexion() {
		super();
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setSize(1000, 400);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		
		crearComponentes();
		
		setVisible(true);
	}

	private void crearComponentes() {
		lblIp= new JLabel("IP");
		lblPuerto= new JLabel("Puerto");
		lblUsuario= new JLabel("Usuario");
		lblContraseña= new JLabel("Contraseña");
		
		lblIp.setBounds(10, 11, 113, 14);
		lblPuerto.setBounds(10, 52, 113, 14);
		lblUsuario.setBounds(10, 93, 113, 14);
		lblContraseña.setBounds(10, 134, 113, 14);
		
		
		panel.add(lblIp);
		panel.add(lblPuerto);
		panel.add(lblUsuario);
		panel.add(lblContraseña);

		
		ip=new JTextField("127.0.0.1");
		puerto=new JTextField("3306");
		usuario=new JTextField("root");
		contraseña=new JTextField("pass");
		
		ip.setBounds(133, 8, 164, 20);
		puerto.setBounds(133, 49, 164, 20);
		usuario.setBounds(133, 90, 164, 20);
		contraseña.setBounds(133, 131, 164, 20);
		
		panel.add(ip);
		panel.add(puerto);
		panel.add(usuario);
		panel.add(contraseña);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(10, 291, 89, 23);
		panel.add(btnAgregar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAgregar) {
			System.out.println("algo");
			conexion=new Conexiones(new DAOSQLFactory());
			conexion.guardarConexionBase(ip.getText(), puerto.getText(), usuario.getText(), contraseña.getText());
			new Main();
			this.dispose();
			
		}
		
	}
}
