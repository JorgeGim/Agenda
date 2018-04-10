package presentacion.vista;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import modelo.Agenda;
import modelo.Conexiones;
import modelo.Contacto;
import modelo.Localidad;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;

public class Menu extends JFrame implements ActionListener {
	
	private JPanel panel;
	private JLabel lblBienvenida;
	private JButton bttAgenda;
	private JButton bttConfig;
	
	public Menu()
	{
		super();
		initialize();
	}
	
	private void initialize()
	{
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
	
	private void crearComponentes()
	{
		lblBienvenida = new JLabel("Bienvenido a la Agenda APP, ¿Qué desea hacer?");
		lblBienvenida.setBounds(320, 93, 400, 14);
		panel.add(lblBienvenida);
		
		bttAgenda = new JButton("Ir a Agenda");
		bttAgenda.setBounds(320, 140, 110, 23);
		bttAgenda.addActionListener(this);
		panel.add(bttAgenda);
		
		bttConfig = new JButton("Ir a configuración");
		bttConfig.setBounds(450, 140, 150, 23);
		bttConfig.addActionListener(this);
		panel.add(bttConfig);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bttAgenda)
		{
			if(Conexiones.comprobarConexion()) 
			{
				Vista vista = new Vista();
				Agenda modelo = new Agenda(new DAOSQLFactory());
				Localidad modelo_localidad = new Localidad(new DAOSQLFactory());
				Contacto modelo_contacto = new Contacto(new DAOSQLFactory());
				Controlador controlador = new Controlador(vista, modelo,modelo_localidad,modelo_contacto);
				controlador.inicializar();
			}
		}
		else if (e.getSource() == bttConfig)
		{
			new VentanaConexion();
		}	
	}
}
