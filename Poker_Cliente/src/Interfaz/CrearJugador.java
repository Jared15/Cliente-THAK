package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class CrearJugador extends JFrame {

	private JPanel contentPane;
	private JTextField crearTF_Nombre;
	private JLabel crearLBL_Contrasena;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearJugador frame = new CrearJugador();
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
	public CrearJugador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel crearLBL_Nombre = new JLabel("Nombre Usuario");
		crearLBL_Nombre.setBounds(43, 45, 206, 15);
		contentPane.add(crearLBL_Nombre);
		
		crearTF_Nombre = new JTextField();
		crearTF_Nombre.setBounds(43, 67, 206, 19);
		contentPane.add(crearTF_Nombre);
		crearTF_Nombre.setColumns(10);
		
		crearLBL_Contrasena = new JLabel("Nombre Usuario");
		crearLBL_Contrasena.setBounds(43, 98, 206, 15);
		contentPane.add(crearLBL_Contrasena);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(43, 120, 206, 19);
		contentPane.add(textField);
	}
}
