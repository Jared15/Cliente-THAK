package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Conexion.RMI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class Denuncio extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	RMI rmi;
	String jugador;
	Jugadores jugadores;

	/**
	 * Create the frame.
	 * @param jugadores 
	 * @param rmi1 
	 */
	public Denuncio(RMI rmi1, Jugadores jugadores1,String jugador1 ){
		jugadores=jugadores1;
		rmi=rmi1;
		jugador=jugador1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#3b0707"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMotivo = new JLabel("Motivo");
		lblMotivo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMotivo.setForeground(UIManager
				.getColor("Table.focusCellBackground"));
		lblMotivo.setBounds(53, 27, 67, 22);
		contentPane.add(lblMotivo);
		
		textField = new JTextField();
		textField.setBounds(143, 31, 194, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescripcion.setBounds(130, 62, 104, 22);
		contentPane.add(lblDescripcion);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(53, 95, 297, 230);
		contentPane.add(textArea);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rmi.registrarDenuncio(jugador,textField.getText(),textArea.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(74, 336, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugadores.setVisible(true);
				try {
					getClase().finalize();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCancelar.setBounds(213, 336, 89, 23);
		contentPane.add(btnCancelar);
	}
	Denuncio getClase(){
		return this;
	}
}
