package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PokerInterfaz extends JFrame {

	private JPanel panel;
	private JTextField campoUsuario;
	private JTextField campoContrasena;
	private JLabel label;
	private JLabel fondo;
	private sesion sesion;
	private JButton btnCrearUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerInterfaz frame = new PokerInterfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	CrearJugador crear;
	
	
	
	public PokerInterfaz() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("Droid Sans", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(66, 95, 122, 15);
		panel.add(lblNewLabel_1);
		
		label = new JLabel("Contrasena");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Droid Sans", Font.BOLD, 20));
		label.setBounds(66, 170, 119, 15);
		panel.add(label);
		
		campoUsuario = new JTextField();
		campoUsuario.setBounds(66, 191, 186, 27);
		panel.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		campoContrasena = new JTextField();
		campoContrasena.setColumns(10);
		campoContrasena.setBounds(66, 116, 186, 27);
		panel.add(campoContrasena);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sesion= new sesion();
				sesion.setVisible(true);
			}
		});
		btnIniciarSesion.setFont(new Font("Droid Sans", Font.BOLD, 20));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBackground(new Color(51, 102, 204));
		btnIniciarSesion.setBounds(66, 243, 183, 34);
		panel.add(btnIniciarSesion);
		
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crear= new CrearJugador();
				crear.setVisible(true);
			}
		});
		btnCrearUsuario.setForeground(Color.WHITE);
		btnCrearUsuario.setFont(new Font("Droid Sans", Font.BOLD, 20));
		btnCrearUsuario.setBackground(new Color(51, 102, 204));
		btnCrearUsuario.setBounds(336, 243, 183, 34);
		panel.add(btnCrearUsuario);
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("gui.png"));
		fondo.setBounds(0, 25, 588, 363);
		panel.add(fondo);
	}
}
