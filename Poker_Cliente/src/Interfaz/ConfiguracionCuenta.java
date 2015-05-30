package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import Conexion.RMI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import javax.swing.UIManager;

public class ConfiguracionCuenta extends JFrame {

	private JPanel contentPane;
	JComboBox comboBox;
	JTextField passActualizar_TF;
	RMI rmi;
	String nu;
	private JButton button;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Create the frame.
	 */
	public ConfiguracionCuenta(RMI rmi1,String nombreUsuario) {
		nu=nombreUsuario;
		this.rmi=rmi1;
		String pass=null;
		try {
			pass=rmi.getPass(nu);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 314);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("TabbedPane.focus"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel crearLBL_Nombre = new JLabel("Password");
		crearLBL_Nombre.setBounds(43, 45, 206, 15);
		contentPane.add(crearLBL_Nombre);
		
		passActualizar_TF = new JTextField(pass);
		passActualizar_TF.setBounds(43, 67, 148, 19);
		contentPane.add(passActualizar_TF);
		passActualizar_TF.setColumns(10);
		
		JLabel crearLBL_Contrasena = new JLabel("Avatar");
		crearLBL_Contrasena.setBounds(43, 98, 206, 15);
		contentPane.add(crearLBL_Contrasena);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Homero", "Yoda", "Poker Face","Linux","Mario", "Luigi"}));
		comboBox.setBounds(43, 120, 148, 24);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				switch(comboBox.getSelectedIndex()){
				case 0:
					rmi.actualizarJugador(nu,passActualizar_TF.getText(),"avatares/homero.png");
					break;
				case 1:
					rmi.actualizarJugador(nu,passActualizar_TF.getText(),"avatares/yoda.png");
					break;
				case 2:					
					rmi.actualizarJugador(nu,passActualizar_TF.getText(),"avatares/pokerface.png");
					
					break;
				case 3:
					rmi.actualizarJugador(nu,passActualizar_TF.getText(),"avatares/linux.png");
					break;
				case 4:
					rmi.actualizarJugador(nu,passActualizar_TF.getText(),"avatares/mario.png");
					break;
				case 5:
					rmi.actualizarJugador(nu,passActualizar_TF.getText(),"avatares/luigi.png");
					break;
				}
				
				JOptionPane.showMessageDialog(null, "Se Actualizo exitosamente el jugador");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnNewButton.setBounds(43, 206, 128, 47);
		contentPane.add(btnNewButton);
		
		button = new JButton("Cerrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button.setBounds(227, 206, 128, 47);
		contentPane.add(button);
		
		button_1 = new JButton("Eliminar Cuenta");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rmi.eliminarJugador(nu);
					JOptionPane.showMessageDialog(null, "Se Elimino el Jugador con Exito");
					setVisible(false);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(43, 160, 148, 19);
		contentPane.add(button_1);

	}
}
