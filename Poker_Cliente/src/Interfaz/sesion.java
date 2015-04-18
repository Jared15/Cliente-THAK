package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sesion extends JFrame {

	private JPanel contentPane;
	private juego juego;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sesion frame = new sesion();
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
	public sesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConfiguraciones = new JButton("Configuraciones");
		btnConfiguraciones.setBounds(428, 333, 117, 25);
		contentPane.add(btnConfiguraciones);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(247, 333, 117, 25);
		contentPane.add(btnCrear);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				juego= new juego();
				juego.setVisible(true);
			}
		});
		btnUnirse.setBounds(68, 333, 117, 25);
		contentPane.add(btnUnirse);
		
		JLabel lblJavierfvasquezbel = new JLabel("JavierFVasquez");
		lblJavierfvasquezbel.setForeground(Color.WHITE);
		lblJavierfvasquezbel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblJavierfvasquezbel.setBounds(56, 271, 197, 15);
		contentPane.add(lblJavierfvasquezbel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("homero.png"));
		label.setBounds(56, 73, 206, 198);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 18, 714, 388);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("gui.png"));
	}
}
