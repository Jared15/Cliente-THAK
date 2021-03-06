package Interfaz;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Conexion.RMI;
import Controlador.Cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class IniciarSesion extends JFrame {

	private JPanel panel;
	private JTextField campoUsuario;
	private JPasswordField campoContrasena;
	private JLabel label;
	private JLabel fondo;
	private DatosSesion sesion;
	/**
	 * 
	 * @return instancia de DatosSesion de IniciarSesion
	 */
	public DatosSesion getSesion() {
		return sesion;
	}


/**
 * Establece la sesion para IniciarSesion
 * @param sesion sesion a establecer
 */
	public void setSesion(DatosSesion sesion) {
		this.sesion = sesion;
	}



	private JButton btnCrearUsuario;
	static RMI rmi;

	
	CrearJugador crear;
	
	
	/**
	 * Crea la instancia de la interfaz con todos sus componentes
	 * @param rmi1 interface con los metodos de la conexion al servidor
	 * @param client Intancia de Cliente asociado a IniciarSesion
	 */
	public IniciarSesion(RMI rmi1, final Cliente client)  {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle("Thak");
		rmi=rmi1;
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
		campoUsuario.setBounds(66, 116, 186, 27);
		panel.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		campoContrasena = new JPasswordField();
		campoContrasena.setColumns(10);
		campoContrasena.setBounds(66, 191, 186, 27);
		panel.add(campoContrasena);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			/**
			 * envia peticion de verificacion de usuarion al servidor
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
				if(rmi.verificarJugador(campoUsuario.getText(), campoContrasena.getText())){
					if(rmi.esAdministrador(campoUsuario.getText())){
						Administrador admin= new Administrador(rmi);
						admin.setVisible(true);
					}else{
						sesion= new DatosSesion(rmi,campoUsuario.getText(),client,getClase());
						sesion.setVisible(true);
						
					}
					setVisible(false);
					
				}else
					JOptionPane.showMessageDialog(null, "El Usuario o la contrasena no son Validos");
				
					
				} catch (RemoteException e) {

					e.printStackTrace();
				}
			}

			
		});
		btnIniciarSesion.setFont(new Font("Droid Sans", Font.BOLD, 20));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBackground(new Color(51, 102, 204));
		btnIniciarSesion.setBounds(66, 243, 183, 34);
		panel.add(btnIniciarSesion);
		
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			/**
			 * abre la interfaz para crear un usuario
			 */
			public void actionPerformed(ActionEvent arg0) {
				crear= new CrearJugador(rmi);
				crear.setVisible(true);
			}
		});
		btnCrearUsuario.setForeground(Color.WHITE);
		btnCrearUsuario.setFont(new Font("Droid Sans", Font.BOLD, 20));
		btnCrearUsuario.setBackground(new Color(51, 102, 204));
		btnCrearUsuario.setBounds(336, 243, 183, 34);
		panel.add(btnCrearUsuario);
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("guis/guiAzul.png"));
		fondo.setBounds(0, 25, 588, 363);
		panel.add(fondo);
		getRootPane().setDefaultButton(btnIniciarSesion);
	}


/**
 * envia la notificacion de ganador a sesion
 * @param ganador id del jugador ganador
 */
	public void ganador(int ganador) {
		sesion.ganador(ganador);
		
	}
	/**
	 * 
	 * @return instancia de IniciarSesion actual
	 */
	private IniciarSesion getClase() {
		
		return this;
	}

}
