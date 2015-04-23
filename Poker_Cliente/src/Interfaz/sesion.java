package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import Conexion.RMI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexion.RMI;

import javax.swing.JComboBox;

public class sesion extends JFrame {

	private JPanel contentPane;
	private juego juego;
	private ConfiguracionCuenta cc;
	ConfiguracionMesa cm;
	JLabel lblNewLabel;
	JComboBox comboBox;
	ConfiguracionMesa Cm;
	public ConfiguracionMesa getCm() {
		return cm;
	}

	public void setCm(ConfiguracionMesa cm) {
		this.cm = cm;
	}

	public ConfiguracionCuenta getCc() {
		return cc;
	}

	public void setCc(ConfiguracionCuenta cc) {
		this.cc = cc;
	}

	private RMI rmi;
	JLabel label;
	JLabel lblNombreUsuario;
	JLabel label_1;
	JLabel label_2;
	String nu;
	/**
	 * Create the frame.
	 * @throws RemoteException 
	 */
	public sesion(RMI rmi1, String nombreUsuario) throws RemoteException {
		nu=nombreUsuario;
		rmi=rmi1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConfiguraciones = new JButton("Configuraciones");
		btnConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cc= new ConfiguracionCuenta(rmi,nu);
				cc.setVisible(true);
			}
		});
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Azul", "Rojo","Verde","Morado"}));
		comboBox.setBounds(384, 18, 136, 16);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setIcon(new ImageIcon("guis/gui"+(String)comboBox.getSelectedItem()+".png"));
			}
		});
		btnNewButton.setBounds(531, 18, 54, 16);
		contentPane.add(btnNewButton);
		btnConfiguraciones.setBounds(81, 349, 206, 25);
		contentPane.add(btnConfiguraciones);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(299, 312, 117, 25);
		contentPane.add(btnCrear);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.addJugador(nu);
				
				cm= new ConfiguracionMesa(rmi,nu);
				cm.setVisible(true);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUnirse.setBounds(170, 312, 117, 25);
		contentPane.add(btnUnirse);
		
		JButton button = new JButton("Cerrar Sesion");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		button.setBounds(299, 349, 206, 25);
		contentPane.add(button);
		
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
	
public juego getJuego() {
		return juego;
	}

	public void setJuego(juego juego) {
		this.juego = juego;
	}

public void mostrarAvatarSesion(String nombreUsuario) throws RemoteException{
	List<String> j=rmi.traerAvatar(nombreUsuario);
	lblNombreUsuario.setText(j.get(0));
	label_1.setText(j.get(1));
	label_2.setText(j.get(2));
	label.setIcon(new ImageIcon(j.get(3)));
}
}
