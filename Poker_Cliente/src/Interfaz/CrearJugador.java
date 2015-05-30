package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import Conexion.RMI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CrearJugador extends JFrame {

	private JPanel contentPane;
	private JTextField crearTF_Nombre;
	private JLabel crearLBL_Contrasena;
	private JTextField textField;
	JComboBox comboBox;
	RMI rmi;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public CrearJugador(RMI rmi1) {
		this.rmi=rmi1;
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
		
		crearLBL_Contrasena = new JLabel("Password");
		crearLBL_Contrasena.setBounds(43, 98, 206, 15);
		contentPane.add(crearLBL_Contrasena);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(43, 120, 206, 19);
		contentPane.add(textField);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rmi.verificarJugador(crearTF_Nombre.getText(), textField.getText())){
					switch(comboBox.getSelectedIndex()){
					case 0:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/homero.png");
						break;
					case 1:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/anonimo.png");
						break;
					case 2:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/pokerface.png");
						break;
					case 3:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/linux.png");
						break;
					case 4:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/mario.png");
						break;
					case 5:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/luigi.png");
						break;
					case 6:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/mario8bits.png");
						break;
					case 7:
						rmi.crearJugador(crearTF_Nombre.getText(), textField.getText(),"avatares/mmm.png");
						break;
					}
					JOptionPane.showMessageDialog(null, "Se creo exitosamente el jugador "+crearTF_Nombre.getText());
					}else
						JOptionPane.showMessageDialog(null, "El jugador "+crearTF_Nombre.getText()+" ya Existe");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCrear.setBounds(43, 217, 117, 25);
		contentPane.add(btnCrear);
		
		JButton button = new JButton("Salir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
			}
		});
		button.setBounds(239, 217, 117, 25);
		contentPane.add(button);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Homero", "Anonimo", "Poker Face","Linux","Mario", "Luigi","mario8bits","mmm"}));
		comboBox.setBounds(43, 162, 206, 24);
		contentPane.add(comboBox);
		
		JLabel lblAvatar = new JLabel("Avatar");
		lblAvatar.setBounds(43, 148, 206, 15);
		contentPane.add(lblAvatar);
		
		JLabel lblNewLabel7 = new JLabel("");
		lblNewLabel7.setBounds(5, 18, 714, 388);
		contentPane.add(lblNewLabel7);
		lblNewLabel7.setIcon(new ImageIcon("guis/guiAzul.png"));
	}
}
