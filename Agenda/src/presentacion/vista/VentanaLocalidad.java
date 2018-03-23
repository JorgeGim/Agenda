package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


import presentacion.controlador.Controlador;

public class VentanaLocalidad extends JFrame{

	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private JPanel contentPane;
	private JPanel panel;

	public VentanaLocalidad(Controlador controlador) {
		
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 343, 183);
		setBounds(100, 100, 450, 300);
		setSize(1000,400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 307, 123);
		panel.setSize(1000, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
	}
}
