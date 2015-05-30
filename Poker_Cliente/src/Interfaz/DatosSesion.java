package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import Conexion.RMI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.RunElement;

import Conexion.RMI;
import Controlador.Cliente;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class DatosSesion extends JFrame {

	private JPanel contentPane;
	private MesaJuego juego;
	private ConfiguracionCuenta cc;
	ConfiguracionMesa cm;
	JLabel lblNewLabel;
	JComboBox comboBox;
	ConfiguracionMesa Cm;

	/**
	 * 
	 * @return Configuracion de la mesa
	 */
	public ConfiguracionMesa getCm() {
		return cm;
	}

	/**
	 * Establece la configuracion de la mesa cm, a la interface DatosSesion
	 * 
	 * @param cm
	 */
	public void setCm(ConfiguracionMesa cm) {
		this.cm = cm;
	}

	/**
	 * 
	 * @return configuracion cuenta de la interface DatosSesion
	 */
	public ConfiguracionCuenta getCc() {
		return cc;
	}

	/**
	 * Establece la configuracion cuenta a DatosSesion
	 * 
	 * @param cc
	 */
	public void setCc(ConfiguracionCuenta cc) {
		this.cc = cc;
	}

	private RMI rmi;
	JLabel label;
	JLabel lblNombreUsuario;
	JLabel label_1;
	JLabel label_2;
	String nu;
	Cliente cliente;
	IniciarSesion iniciarSesion;
	String fondo;

	/**
	 * Constructor de DatosSesion
	 * @param rmi1 interfaz con los metodos para la conexion con el servidor
	 * @param nombreUsuario nombre del usuario que esta ejecutando la interfaz
	 * @param client instancia de cliente asociada a la instancia que se va a crear de DatosCliente
	 * @param iniciarSesion1 instancia de IniciarSesion asociada a la instancia que se va a crear de DatosCliente
	 * @throws RemoteException exepcion de la conexion con el servidor
	 */
	public DatosSesion(RMI rmi1, String nombreUsuario, Cliente client,
			IniciarSesion iniciarSesion1) throws RemoteException {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle("Thak");
		nu = nombreUsuario;
		rmi = rmi1;
		cliente = client;
		iniciarSesion = iniciarSesion1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnConfiguraciones = new JButton("Configuraciones");
		btnConfiguraciones.addActionListener(new ActionListener() {
			/**
			 * Boton que abre la interfaz configuracionCuenta
			 */
			public void actionPerformed(ActionEvent arg0) {
				cc = new ConfiguracionCuenta(rmi, nu);
				cc.setVisible(true);
			}
		});

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			/**
			 * Actualiza el fondo de la interfaz con el color seleccionado por el usuario
			 */
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setIcon(new ImageIcon("guis/gui"
						+ (String) comboBox.getSelectedItem() + ".png"));
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Azul",
				"Rojo", "Verde", "Morado" }));
		comboBox.setBounds(384, 18, 136, 16);
		contentPane.add(comboBox);
		btnConfiguraciones.setBounds(81, 349, 117, 25);
		contentPane.add(btnConfiguraciones);

		JButton btnCrear = new JButton("Manual");
		btnCrear.addActionListener(new ActionListener() {
			/**
			 * abre el manual de usuario con el lector de pdf
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec("cmd /c start manual.pdf");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnCrear.setBounds(237, 312, 117, 25);
		contentPane.add(btnCrear);

		JButton btnUnirse = new JButton("Jugar");
		btnUnirse.addActionListener(new ActionListener() {
			/**
			 * abre la interfaz para configurar la mesa de juego
			 */
			public void actionPerformed(ActionEvent arg0) {

				try {
					fondo = "guis/gui" + (String) comboBox.getSelectedItem()
							+ ".png";
					boolean iniciado = rmi.isIniciada();
					if (!iniciado) {
						rmi.addJugador(nu);
						DatosSesion este = getEste();
						cm = new ConfiguracionMesa(rmi, nu, este, fondo);
						cm.setVisible(true);
						setVisible(false);
						rmi.actualizarLista();
					} else {
						JOptionPane.showMessageDialog(null,
								"El juego ya ha iniciado");
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});
		btnUnirse.setBounds(81, 312, 117, 25);
		contentPane.add(btnUnirse);

		JButton button = new JButton("Cerrar Sesion");
		button.addActionListener(new ActionListener() {
			/**
			 * vuelve a la pantalla de IniciarSesion
			 */
			public void actionPerformed(ActionEvent arg0) {

				try {
					int b = JOptionPane.showConfirmDialog(null,
							"Va a cerrar sesion");
					if (b == 0) {
						setVisible(false);
						rmi.deleteObserver(cliente);
						iniciarSesion.setVisible(true);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});
		button.setBounds(237, 349, 110, 25);
		contentPane.add(button);

		JButton btnManos = new JButton("Manos");
		btnManos.addActionListener(new ActionListener() {
			/**
			 * muestra la clasificacion de manos
			 */
			public void actionPerformed(ActionEvent arg0) {
				Clasificacion_manos clasificacionmanos = new Clasificacion_manos();
				clasificacionmanos.setVisible(true);
			}
		});
		btnManos.setBounds(388, 312, 117, 25);
		contentPane.add(btnManos);

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

		JButton btnTomarNotas = new JButton("Tomar Notas");
		btnTomarNotas.addActionListener(new ActionListener() {
			/**
			 * provee un espacio para agregar una nota y hace la peticion al servidor para agregar la nota
			 */
			public void actionPerformed(ActionEvent e) {
				String nota = JOptionPane.showInputDialog("esciba aqui", null);
				try {
					rmi.agregarNota(nota, nu);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTomarNotas.setBounds(387, 350, 118, 23);
		contentPane.add(btnTomarNotas);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 18, 714, 388);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("guis/guiAzul.png"));
		mostrarAvatarSesion(nombreUsuario);
		getRootPane().setDefaultButton(btnUnirse);
	}
	/**
	 * 
	 * @return la instancia de la interfaz DatosSesion actual
	 */
	DatosSesion getEste() {
		return this;
	}
	/**
	 * 
	 * @return Juego asociado a DatosSesion actual
	 */
	public MesaJuego getJuego() {
		return juego;
	}
	/**
	 * 
	 * @param juego establece la mesa de juego a la interfaz DatosSesion actual
	 */
	public void setJuego(MesaJuego juego) {
		this.juego = juego;
	}

/**
 * muestra el avatar o imagen del jugador que inicio sesion
 * @param nombreUsuario nombre del usuario que inicio sesion
 * @throws RemoteException exepcion de conexion con el servidor
 */
	public void mostrarAvatarSesion(String nombreUsuario)
			throws RemoteException {
		List<String> j = rmi.traerAvatar(nombreUsuario);
		lblNombreUsuario.setText(j.get(0));
		label_1.setText(j.get(1));
		label_2.setText(j.get(2));
		label.setIcon(new ImageIcon(j.get(3)));
	}
	/**
	 * notifica un ganador a la Interfaz configuracion mesa
	 * @param ganador identificador del jugador que gano la ronda
	 */
	public void ganador(int ganador) {
		cm.ganador(ganador);

	}
}
