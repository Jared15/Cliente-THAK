package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexion.RMI;

public class Detalles extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;
	Jugadores jugadores;
	private RMI rmi;
	JLabel label;
	JLabel lblNombreUsuario;
	JLabel label_1;
	JLabel label_2;
	String nu;

	/**
	 * Create the frame.
	 * @param jugadores 
	 * @param client 
	 * 
	 * @throws RemoteException
	 */
	public Detalles(RMI rmi1, String nombreUsuario, Jugadores jugadores) throws RemoteException {
		nu = nombreUsuario;
		rmi = rmi1;
		this.jugadores=jugadores;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombreUsuario = new JLabel("NombreUsuario");
		lblNombreUsuario.setForeground(Color.WHITE);
		lblNombreUsuario.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNombreUsuario.setBounds(56, 271, 216, 29);
		contentPane.add(lblNombreUsuario);

		label = new JLabel("");

		label.setBounds(56, 73, 206, 198);
		contentPane.add(label);

		label_1 = new JLabel("XXXXXXX");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		label_1.setBounds(260, 163, 216, 29);
		contentPane.add(label_1);

		JLabel label_4 = new JLabel("Puntos");
		label_4.setForeground(Color.GRAY);
		label_4.setFont(new Font("Arial Black", Font.BOLD, 20));
		label_4.setBounds(260, 127, 216, 29);
		contentPane.add(label_4);

		label_2 = new JLabel("XXXXXXXXX");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		label_2.setBounds(260, 259, 216, 29);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Partidas Ganadas");
		label_3.setForeground(Color.GRAY);
		label_3.setFont(new Font("Arial Black", Font.BOLD, 20));
		label_3.setBounds(260, 230, 216, 29);
		contentPane.add(label_3);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 18, 714, 388);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("guis/guiAzul.png"));
		mostrarAvatarSesion(nombreUsuario);
	}
	Detalles getEste(){
		return this;
	}


	/**
	 * muestra el avatar o imagen del jugador que inicio sesion
	 * 
	 * @param nombreUsuario
	 *            ( el nombre del usuario que inicio sesion
	 * @throws RemoteException
	 */
	public void mostrarAvatarSesion(String nombreUsuario)
			throws RemoteException {
		List<String> j = rmi.traerAvatar(nombreUsuario);
		lblNombreUsuario.setText(j.get(0));
		label_1.setText(j.get(1));
		label_2.setText(j.get(2));
		label.setIcon(new ImageIcon(j.get(3)));
	}


}
