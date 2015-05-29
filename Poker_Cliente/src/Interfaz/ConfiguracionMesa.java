package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

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
	String nu;
	private JComboBox comboBox_1;
	private JLabel label_1;
	private DatosSesion sesion;

	public MesaJuego getJuego() {
		return juego;
	}

	public void setJuego(MesaJuego juego) {
		this.juego = juego;
	}

	public ConfiguracionMesa(RMI rmi1, String nu1, final DatosSesion sesion)
			throws RemoteException {
		this.rmi = rmi1;
		this.nu = nu1;
		this.sesion = sesion;
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
			public void actionPerformed(ActionEvent e) {
				try {
					rmi.iniciarPartida();
					rmi.llenarMazo(1);
					rmi.getListaCartasMesa();
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

		buttonActualizarJugadores = new JButton("Actualizar Lista de Jugadores");
		buttonActualizarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarJugadores();
			}

			/**
			 * 
			 */

		});
		buttonActualizarJugadores.setBounds(46, 334, 377, 55);
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
	}

	public void ganador(int ganador) {
		juego.ganador(ganador);

	}

	public void iniciarPartida() {
		try {
			lj = rmi.getListaJugadores();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (lj.size() > 1 && lj.size() < 7) {
			try {
				juego = new MesaJuego(rmi, nu, sesion);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
				juego.setColorMesa("mesas/mesaazul.png");
				break;
			case 1:
				juego.setColorMesa("mesas/mesaroja.png");
				break;
			case 2:
				juego.setColorMesa("mesas/mesaverde.png");
				break;
			}
			juego.setVisible(true);
			setVisible(false);
		} else
			JOptionPane.showInternalMessageDialog(contentPane,
					"Numero de Juagdores Incorrectos",
					"Somos muy pocos.... o muchos?", JOptionPane.ERROR_MESSAGE);

	}
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
