package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexion.RMI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JTextField;

public class ConfiguracionMesa extends JFrame {

	private JPanel contentPane;
	RMI rmi;
	private JTable table;
	JComboBox comboBox;
	MesaJuego juego;
	private JButton buttonActualizarJugadores;
	List<List<String>> lj;
	DefaultTableModel modelo;
	Object[] object;
	String nombreUsuario;
	private JComboBox comboBox_1;
	private JLabel label_1;
	private DatosSesion sesion;
	private JTextField textField;
	private String fondo;

	/**
	 * 
	 * @return el juego en la variable juego
	 */
	public MesaJuego getJuego() {
		return juego;
	}
	/**
	 * establece el juego que llega como parametro como al juego de la clase
	 * @param juego
	 */
	public void setJuego(MesaJuego juego) {
		this.juego = juego;
	}
	/**
	 *  contructor de la clase ConfiguracionMesa
	 * @param rmi1 interfaz con los metodos para la conexion con el servidor
	 * @param nombreUsuario1 nombre del usuario
	 * @param sesion referencia a la interfaz grafica sesion
	 * @param fondo1 eleccion de fondo del jugador
	 * @throws RemoteException
	 */
	public ConfiguracionMesa(RMI rmi1, String nombreUsuario1, final DatosSesion sesion,
			String fondo1) throws RemoteException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle("Configuracion de la Mesa");
		this.rmi = rmi1;
		this.nombreUsuario = nombreUsuario1;
		this.sesion = sesion;
		fondo = fondo1;
		lj = rmi.getListaJugadores();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 946, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#3b0707"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblJugadores = new JLabel("Jugadores");
		lblJugadores.setFont(new Font("Dialog", Font.BOLD, 16));
		lblJugadores.setForeground(UIManager
				.getColor("Table.focusCellBackground"));
		lblJugadores.setBounds(53, 27, 132, 22);
		contentPane.add(lblJugadores);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Azul",
				"Roja", "Verde" }));
		comboBox.setBounds(619, 86, 207, 24);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Iniciar Partida");
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * boton enviar la solicitud de iniciar la partida al servidor
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					if (lj.size() > 1 && lj.size() < 7) {
						rmi.iniciarPartida();
						rmi.llenarMazo(1);
						rmi.getListaCartasMesa();
					} else
						JOptionPane.showInternalMessageDialog(contentPane,
								"Numero de Juagdores Incorrectos",
								"Somos muy pocos.... o muchos?",
								JOptionPane.ERROR_MESSAGE);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnNewButton.setBounds(619, 331, 221, 55);
		contentPane.add(btnNewButton);

		table = new JTable();
		table.setBounds(46, 63, 377, 259);
		contentPane.add(table);

		modelo = new DefaultTableModel();

		modelo.addColumn("Nombre");
		modelo.addColumn("Puntos");
		modelo.addColumn("Partidas Ganadas");

		for (List<String> ls : lj) {

			object = new Object[3];
			object[0] = ls.get(0);
			object[1] = ls.get(1);
			object[2] = ls.get(2);

			modelo.addRow(object);
		}
		table.setModel(modelo);

		JLabel label = new JLabel("Color Mesa");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBounds(621, 52, 132, 22);
		contentPane.add(label);

		buttonActualizarJugadores = new JButton("cambiar dinero  ");
		buttonActualizarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarJugadores();
			}

			/**
			 * 
			 */

		});
		buttonActualizarJugadores.setBounds(126, 378, 163, 32);
		contentPane.add(buttonActualizarJugadores);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Estilo 1",
				"Estilo 2" }));
		comboBox_1.setBounds(619, 191, 207, 24);
		contentPane.add(comboBox_1);

		label_1 = new JLabel("Estilo Cartas");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.BOLD, 16));
		label_1.setBounds(621, 157, 132, 22);
		contentPane.add(label_1);
		getRootPane().setDefaultButton(buttonActualizarJugadores);

		JLabel lblDineroParaLa = new JLabel("dinero para la mesa");
		lblDineroParaLa.setForeground(Color.WHITE);
		lblDineroParaLa.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDineroParaLa.setBounds(53, 344, 163, 22);
		contentPane.add(lblDineroParaLa);

		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBounds(235, 346, 176, 21);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	/**
	 * 
	 * @param ganador identificador del jugador que gano la mano completa
	 */
	public void ganador(int ganador) {
		juego.ganador(ganador);

	}
	/**
	 * inicia la partida con las elecciones del usuario
	 */
	public void iniciarPartida() {
		try {
			lj = rmi.getListaJugadores();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			juego = new MesaJuego(rmi, nombreUsuario, sesion);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String mesa = "mesas/mesaazul.png";
		int carta = comboBox_1.getSelectedIndex() + 1;
		switch (comboBox_1.getSelectedIndex()) {
		case 0:
			juego.setEstiloCarta(1);
			break;
		case 1:
			juego.setEstiloCarta(2);
			break;
		}

		switch (comboBox.getSelectedIndex()) {
		case 0:
			mesa = "mesas/mesaazul.png";
			juego.setColorMesa("mesas/mesaazul.png");
			break;
		case 1:
			mesa = "mesas/mesaroja.png";
			juego.setColorMesa("mesas/mesaroja.png");
			break;
		case 2:
			mesa = "mesas/mesaverde.png";
			juego.setColorMesa("mesas/mesaverde.png");
			break;
		}
		try {
			rmi.guardarColores(fondo, carta, mesa, nombreUsuario);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		juego.setVisible(true);
		setVisible(false);

	}
	/**
	 * actualiza la tabla con los jugadores presentes en la mesa
	 */
	public void actualizarJugadores() {
		try {
			lj = rmi.getListaJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelo = new DefaultTableModel();

		modelo.addColumn("Nombre");
		modelo.addColumn("Puntos");
		modelo.addColumn("Partidas Ganadas");
		object = new Object[3];

		for (List<String> ls : lj) {
			for (int i = 0; i < 3; i++) {
				object[i] = ls.get(i);
			}
			modelo.addRow(object);
		}
		table.setModel(modelo);
	}
}
