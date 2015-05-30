package Interfaz;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Conexion.RMI;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class MesaJuego extends JFrame {

	JLabel ficha;
	private JPanel contentPane;
	private JLabel carta1;
	private JLabel carta2;
	private JLabel carta3;
	private JLabel carta4;
	private JLabel carta5;
	private JLabel fondo;
	private String path;
	private List<String> cartas = new ArrayList<String>();

	private JButton apostarBtn1;
	private JButton apostarBtn2;
	private JButton apostarBtn3;

	JLabel avatar1;
	JLabel avatar2;
	JLabel avatar3;
	JLabel avatar4;
	JLabel avatar5;
	JLabel avatar6;

	int idJugador;
	int estiloCarta;
	JLabel lblPozo;
	JLabel lblPozoCant;

	private List<JLabel> dineroJugadores = new ArrayList<JLabel>();
	private List<JLabel> apuestaJugadores = new ArrayList<JLabel>();

	/**
	 * obtiene el estilo de carta de la mesa
	 * 
	 * @return el estilo de carta
	 */
	public int getEstiloCarta() {
		return estiloCarta;
	}

	/**
	 * establece el estilo de carta a la mesa de juego
	 * 
	 * @param estiloCarta
	 *            estilo a establecer
	 */
	public void setEstiloCarta(int estiloCarta) {
		this.estiloCarta = estiloCarta;
	}

	RMI rmi;
	List<String> mesa = new ArrayList<String>();
	List<List<String>> listaJugadores;
	String nombreUsuario;

	/**
	 * Obtiene la mesa asociada a mesa de juego
	 * 
	 * @return mesa asociada
	 */
	public List<String> getMesa() {
		return mesa;
	}

	/**
	 * establece la mesa a la mesaJuego actual
	 * 
	 * @param mesa
	 */
	public void setMesa(List<String> mesa) {
		this.mesa = mesa;
	}

	ImageIcon c;
	Icon icono;
	private JLabel carta11;
	private JLabel carta12;
	private JLabel carta21;
	private JLabel carta22;
	private JLabel carta31;
	private JLabel carta32;
	private JLabel carta41;
	private JLabel carta42;
	private JLabel carta51;
	private JLabel carta52;
	private JLabel carta61;
	private JLabel carta62;
	private JTextField textCantidad;
	private JLabel lblGanador;
	private JSlider slider;
	private JLabel fondoApuestas;

	JLabel dinero1;
	JLabel dinero2;
	JLabel dinero3;
	JLabel dinero4;
	JLabel dinero5;
	JLabel dinero6;

	private JLabel fichadinero1;
	private JLabel fichadinero2;
	private JLabel fichadinero3;
	private JLabel fichadinero4;
	private JLabel fichadinero5;
	private JLabel fichadinero6;

	JLabel apuesta1;
	JLabel apuesta2;
	JLabel apuesta3;
	JLabel apuesta4;
	JLabel apuesta5;
	JLabel apuesta6;
	private JButton btnVerJugadores;

	/**
	 * Crea una instancia de Mesa juego con todos sus componentes
	 * 
	 * @param rmi1
	 *            interface con los metodos de la conexion con el servidor
	 * @param nombreUsuario1
	 *            nombre de usuario que inicio sesion
	 * @param sesion
	 *            sesion asociada a la MesaJuego actual
	 * @throws RemoteException
	 *             excepcion de conexion con el servidor
	 */


	public MesaJuego(RMI rmi1, String nombreUsuario1, final DatosSesion sesion)throws RemoteException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle("Thak");


		rmi = rmi1;
		this.nombreUsuario = nombreUsuario1;

		rmi.llenarMazo(1);
		listaJugadores = rmi.getListaJugadores();

		for (int j = 0; j < listaJugadores.size(); j++) {
			if (listaJugadores.get(j).get(0).equalsIgnoreCase(nombreUsuario)) {
				idJugador = j + 1;
			}
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1366, 812);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setBackground(Color.decode("#3b0707"));

		DisplayMode dm = new DisplayMode(1336, 768, 32,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		PantallaCompleta pc = new PantallaCompleta();

		pc.setFullScreen(dm, this);
		contentPane.setLayout(null);

		btnVerJugadores = new JButton("ver jugadores");
		btnVerJugadores.addActionListener(new ActionListener() {
			/**
			 * abre la interfaz Jugadores
			 */
			public void actionPerformed(ActionEvent arg0) {
				Jugadores jugadores = new Jugadores(rmi, getClase());
				jugadores.setVisible(true);
			}

		});
		btnVerJugadores.setBounds(23, 60, 121, 23);
		contentPane.add(btnVerJugadores);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			/**
			 * oculta la interfaz MesaJuego
			 */
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				sesion.setVisible(true);

			}
		});

		btnSalir.setBounds(23, 24, 121, 25);
		contentPane.add(btnSalir);

		lblGanador = new JLabel();
		lblGanador.setFont(new Font("Calibri", Font.BOLD, 50));
		lblGanador.setForeground(Color.WHITE);
		lblGanador.setBounds(350, 100, 1000, 100);
		contentPane.add(lblGanador);
		textCantidad = new JTextField();
		textCantidad.setFont(new Font("Calibri", Font.BOLD, 17));
		textCantidad.setForeground(SystemColor.text);
		textCantidad.setBackground(SystemColor.desktop);
		textCantidad.setBounds(640, 390, 80, 40);
		contentPane.add(textCantidad);
		textCantidad.setColumns(10);

		slider = new JSlider();
		slider.setBackground(SystemColor.desktop);
		slider.addChangeListener(new ChangeListener() {
			/**
			 * actualiza en numero en la apuesto con el cambio en el deslisador
			 */
			public void stateChanged(ChangeEvent arg0) {
				textCantidad.setText(slider.getValue() + "");
			}
		});
		slider.setMinimum(50);
		slider.setMaximum(200);
		slider.setBounds(575, 428, 216, 25);
		contentPane.add(slider);

		apuesta1 = new JLabel("0");
		apuesta1.setForeground(SystemColor.text);
		apuesta1.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta1.setBounds(313, 350, 70, 70);

		apuestaJugadores.add(apuesta1);

		apuesta2 = new JLabel("0");
		apuesta2.setForeground(SystemColor.text);
		apuesta2.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta2.setBounds(370, 460, 70, 70);
		apuestaJugadores.add(apuesta2);

		apuesta3 = new JLabel("0");
		apuesta3.setForeground(SystemColor.text);
		apuesta3.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta3.setBounds(495, 530, 70, 70);
		apuestaJugadores.add(apuesta3);

		apuesta4 = new JLabel("0");
		apuesta4.setForeground(SystemColor.text);
		apuesta4.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta4.setBounds(850, 530, 70, 70);
		apuestaJugadores.add(apuesta4);

		apuesta5 = new JLabel("0");
		apuesta5.setForeground(SystemColor.text);
		apuesta5.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta5.setBounds(996, 460, 70, 70);
		apuestaJugadores.add(apuesta5);

		apuesta6 = new JLabel("0");
		apuesta6.setForeground(SystemColor.text);
		apuesta6.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta6.setBounds(1053, 350, 70, 70);
		apuestaJugadores.add(apuesta6);

		ficha = new JLabel("");
		ficha.setBounds(0, 0, 0, 0);
		ficha.setIcon(new ImageIcon("avatares/halo.png"));
		contentPane.add(ficha);
		setFichaJugador();
		List<JLabel> usuarios = new ArrayList<JLabel>();

		JLabel lblUsuario1 = new JLabel("");
		lblUsuario1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsuario1.setForeground(Color.LIGHT_GRAY);
		lblUsuario1.setBounds(70, 415, 152, 14);
		contentPane.add(lblUsuario1);
		usuarios.add(lblUsuario1);

		JLabel lblUsuario2 = new JLabel("");
		lblUsuario2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsuario2.setForeground(Color.LIGHT_GRAY);
		lblUsuario2.setBounds(131, 671, 152, 14);
		contentPane.add(lblUsuario2);
		usuarios.add(lblUsuario2);

		JLabel lblUsuario3 = new JLabel("");
		lblUsuario3.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsuario3.setForeground(Color.LIGHT_GRAY);
		lblUsuario3.setBounds(450, 740, 132, 14);
		contentPane.add(lblUsuario3);
		usuarios.add(lblUsuario3);

		JLabel lblUsuario4 = new JLabel("");
		lblUsuario4.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsuario4.setForeground(Color.LIGHT_GRAY);
		lblUsuario4.setBounds(905, 740, 192, 14);
		contentPane.add(lblUsuario4);
		usuarios.add(lblUsuario4);

		JLabel lblUsuario5 = new JLabel("");
		lblUsuario5.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsuario5.setForeground(Color.LIGHT_GRAY);
		lblUsuario5.setBounds(1111, 653, 152, 14);
		contentPane.add(lblUsuario5);
		usuarios.add(lblUsuario5);

		JLabel lblUsuario6 = new JLabel("");
		lblUsuario6.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsuario6.setForeground(Color.LIGHT_GRAY);
		lblUsuario6.setBounds(1156, 417, 144, 14);
		contentPane.add(lblUsuario6);
		usuarios.add(lblUsuario6);

		for (JLabel l : usuarios) {
			l.setVisible(false);
		}
		for (int i = 0; i < listaJugadores.size(); i++) {
			usuarios.get(i).setVisible(true);
			usuarios.get(i).setText(listaJugadores.get(i).get(0));
		}

		avatar1 = new JLabel("");
		avatar1.setBounds(50, 190, 160, 220);

		avatar2 = new JLabel("");
		avatar2.setBounds(100, 450, 160, 220);

		avatar3 = new JLabel("");
		avatar3.setBounds(420, 566, 160, 220);

		avatar4 = new JLabel("");
		avatar4.setBounds(850, 566, 160, 220);

		avatar5 = new JLabel("");
		avatar5.setBounds(1100, 450, 160, 220);

		avatar6 = new JLabel("");
		avatar6.setBounds(1150, 190, 160, 220);

		dinero1 = new JLabel("0");
		dinero1.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero1.setBounds(220, 379, 40, 34);

		dineroJugadores.add(dinero1);

		dinero2 = new JLabel("0");
		dinero2.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero2.setBounds(270, 630, 40, 34);
		dineroJugadores.add(dinero2);

		dinero3 = new JLabel("0");
		dinero3.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero3.setBounds(600, 700, 40, 34);
		dineroJugadores.add(dinero3);

		dinero4 = new JLabel("0");
		dinero4.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero4.setBounds(815, 700, 50, 40);
		dineroJugadores.add(dinero4);

		dinero5 = new JLabel("0");
		dinero5.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero5.setBounds(1040, 620, 50, 40);
		dineroJugadores.add(dinero5);

		dinero6 = new JLabel("0");
		dinero6.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero6.setBounds(1100, 381, 50, 31);
		dineroJugadores.add(dinero6);

		fichadinero1 = new JLabel("");
		fichadinero1.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero1.setBounds(190, 348, 100, 100);

		fichadinero2 = new JLabel("");
		fichadinero2.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero2.setBounds(240, 600, 100, 100);

		fichadinero3 = new JLabel("");
		fichadinero3.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero3.setBounds(550 + 20, 670, 100, 100);

		fichadinero4 = new JLabel("");
		fichadinero4.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero4.setBounds(821 - 40, 670, 100, 100);

		fichadinero5 = new JLabel("");
		fichadinero5.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero5.setBounds(1010, 595, 100, 100);

		fichadinero6 = new JLabel("");
		fichadinero6.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero6.setBounds(1070, 348, 100, 100);

		llenarAvatares();
		cartasJugadores();
		System.out.println("IDJUGADOR------------------------------"
				+ idJugador);

		carta1 = new JLabel("");
		carta1.setBounds(440, 260, 80, 100);
		ImageIcon c1 = new ImageIcon("reverso.png");
		Icon icono1 = new ImageIcon(c1.getImage().getScaledInstance(
				carta1.getWidth(), carta1.getHeight(), Image.SCALE_DEFAULT));

		carta1.setIcon(icono1);
		contentPane.add(carta1);

		carta2 = new JLabel("");
		carta2.setBounds(540, 260, 80, 100);
		ImageIcon c2 = new ImageIcon("reverso.png");
		Icon icono2 = new ImageIcon(c2.getImage().getScaledInstance(
				carta2.getWidth(), carta2.getHeight(), Image.SCALE_DEFAULT));
		carta2.setIcon(icono2);
		contentPane.add(carta2);

		carta3 = new JLabel("");
		carta3.setBounds(640, 260, 80, 100);
		ImageIcon c3 = new ImageIcon("reverso.png");
		Icon icono3 = new ImageIcon(c3.getImage().getScaledInstance(
				carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta3.setIcon(icono3);
		contentPane.add(carta3);

		carta4 = new JLabel("");
		carta4.setBounds(740, 260, 80, 100);
		ImageIcon c4 = new ImageIcon("reverso.png");
		Icon icono4 = new ImageIcon(c4.getImage().getScaledInstance(
				carta4.getWidth(), carta4.getHeight(), Image.SCALE_DEFAULT));
		carta4.setIcon(icono4);
		contentPane.add(carta4);

		carta5 = new JLabel("");
		carta5.setBounds(840, 260, 80, 100);
		ImageIcon c5 = new ImageIcon("reverso.png");
		Icon icono5 = new ImageIcon(c5.getImage().getScaledInstance(
				carta5.getWidth(), carta5.getHeight(), Image.SCALE_DEFAULT));
		carta5.setIcon(icono5);
		contentPane.add(carta5);
		// BOTONES APOSTAR
		apostarBtn1 = new JButton();
		apostarBtn1.setBackground(new Color(0, 0, 0));
		apostarBtn1.addActionListener(new ActionListener() {
			/**
			 * envia al servidor la peticion de retiro de la mano del jugador actual
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.retirarse(idJugador);
					;
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		apostarBtn1.setBounds(582, 476, 50, 50);
		apostarBtn1.setIcon(new ImageIcon("Botones/retirarse.png"));
		contentPane.add(apostarBtn1);

		apostarBtn2 = new JButton();
		apostarBtn2.setBackground(new Color(0, 0, 0));
		apostarBtn2.addActionListener(new ActionListener() {
			/**
			 * envia al servidor la peticion de pasar el turno del jugador actual
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.apostar(idJugador, 0);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		apostarBtn2.setBounds(657, 476, 50, 50);
		apostarBtn2.setIcon(new ImageIcon("Botones/pasar.png"));
		contentPane.add(apostarBtn2);

		apostarBtn3 = new JButton();
		apostarBtn3.setBackground(new Color(0, 0, 0));
		apostarBtn3.addActionListener(new ActionListener() {
			/**
			 * envia al servidor la peticion de subir la apuesta actual
			 */
			public void actionPerformed(ActionEvent arg0) {
				int dinero = Integer.parseInt(textCantidad.getText().trim());
				int dineroJug = Integer.parseInt(dineroJugadores
						.get(idJugador - 1).getText().trim());
				if (dinero <= dineroJug) {
					try {
						rmi.apostar(idJugador, dinero);

					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Dinero Insuficiente!");
				}
			}
		});
		apostarBtn3.setBounds(730, 476, 50, 50);
		apostarBtn3.setIcon(new ImageIcon("Botones/subir.png"));
		contentPane.add(apostarBtn3);

		fondoApuestas = new JLabel("");
		fondoApuestas.setIcon(new ImageIcon("Botones/apuestas.png"));
		fondoApuestas.setBounds(564, 375, 235, 90);
		contentPane.add(fondoApuestas);

		lblPozoCant = new JLabel("0");
		lblPozoCant.setForeground(Color.BLACK);
		lblPozoCant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPozoCant.setBounds(650, 161, 192, 73);
		contentPane.add(lblPozoCant);

		lblPozo = new JLabel("");
		lblPozo.setForeground(Color.WHITE);
		lblPozo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblPozo.setBounds(626, 149, 100, 100);
		lblPozo.setIcon(new ImageIcon("fichapozo.png"));
		contentPane.add(lblPozo);

		JLabel dealer = new JLabel("");
		dealer.setIcon(new ImageIcon("dealer1.png"));
		dealer.setBounds(583, 0, 208, 182);
		contentPane.add(dealer);
		fondo = new JLabel("");
		fondo.setBackground(new Color(128, 0, 0));
		contentPane.add(fondo);

		fondo.setBounds(100, 30, 1340, 740);
		
		for (int j = 0; j < listaJugadores.size(); j++) {
			dineroJugadores.get(j).setText(listaJugadores.get(j).get(1));
		}

	}
	/**
	 * inicia las cartas de la mesa en el reverso de la carta
	 */
	private void iniciarCartas() {
		c = new ImageIcon("reverso.png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(),
				carta3.getHeight(), Image.SCALE_DEFAULT));
		carta1.setIcon(icono);
		carta2.setIcon(icono);
		carta3.setIcon(icono);
		carta4.setIcon(icono);
		carta5.setIcon(icono);

	}



	/**
	 * muestra las primeras 3 cartas del juego en la mesa
	 */
	public void cambiarCartas() {
		c = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/" + cartas.get(0)
				+ ".png");

		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(),
				carta3.getHeight(), Image.SCALE_DEFAULT));
		carta1.setIcon(icono);
		c = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/" + cartas.get(1)
				+ ".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(),
				carta3.getHeight(), Image.SCALE_DEFAULT));
		carta2.setIcon(icono);
		c = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/" + cartas.get(2)
				+ ".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(),
				carta3.getHeight(), Image.SCALE_DEFAULT));
		carta3.setIcon(icono);
	}

	/**
	 * muestra la 4ta carta del juego en la mesa
	 */
	public void cuarta() {
		c = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/" + cartas.get(3)
				+ ".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(),
				carta3.getHeight(), Image.SCALE_DEFAULT));
		carta4.setIcon(icono);
	}

	/**
	 * muestra la 5ta carta del juego en la mesa
	 */
	public void quinta() {

		c = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/" + cartas.get(4)
				+ ".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(),
				carta3.getHeight(), Image.SCALE_DEFAULT));
		carta5.setIcon(icono);

	}

	/**
	 * Cambia el color de la mesa para el jugador
	 * 
	 * @param path
	 *            (direccion de la imagen que selecciono para la mesa
	 */
	public void setColorMesa(String path) {
		fondo.setIcon(new ImageIcon(path));
	}

	/**
	 * muestra las imagenes o avatares de los jugadores que ingresaron a la mesa
	 */
	public void llenarAvatares() {

		switch (listaJugadores.size()) {

		case 2:
			avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
			contentPane.add(avatar1);
			avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
			contentPane.add(avatar2);
			contentPane.add(avatar2);
			contentPane.add(dinero1);
			contentPane.add(fichadinero1);
			contentPane.add(dinero2);
			contentPane.add(fichadinero2);

			contentPane.add(apuesta1);
			contentPane.add(apuesta2);
			break;
		case 3:
			avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
			contentPane.add(avatar1);
			avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
			contentPane.add(avatar2);
			avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
			contentPane.add(avatar3);

			contentPane.add(dinero1);
			contentPane.add(fichadinero1);
			contentPane.add(dinero2);
			contentPane.add(fichadinero2);
			contentPane.add(dinero3);
			contentPane.add(fichadinero3);

			contentPane.add(apuesta1);
			contentPane.add(apuesta2);
			contentPane.add(apuesta3);

			break;
		case 4:
			avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
			contentPane.add(avatar1);
			avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
			contentPane.add(avatar2);
			avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
			contentPane.add(avatar3);
			avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
			contentPane.add(avatar4);

			contentPane.add(dinero1);
			contentPane.add(fichadinero1);
			contentPane.add(dinero2);
			contentPane.add(fichadinero2);
			contentPane.add(dinero3);
			contentPane.add(fichadinero3);
			contentPane.add(dinero4);
			contentPane.add(fichadinero4);
			contentPane.add(apuesta1);
			contentPane.add(apuesta2);
			contentPane.add(apuesta3);
			contentPane.add(apuesta4);

			break;
		case 5:
			avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
			contentPane.add(avatar1);
			avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
			contentPane.add(avatar2);
			avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
			contentPane.add(avatar3);
			avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
			contentPane.add(avatar4);
			avatar5.setIcon(new ImageIcon(listaJugadores.get(4).get(3)));
			contentPane.add(avatar5);

			contentPane.add(dinero1);
			contentPane.add(fichadinero1);
			contentPane.add(dinero2);
			contentPane.add(fichadinero2);
			contentPane.add(dinero3);
			contentPane.add(fichadinero3);
			contentPane.add(dinero4);
			contentPane.add(fichadinero4);
			contentPane.add(dinero5);
			contentPane.add(fichadinero5);

			contentPane.add(apuesta1);
			contentPane.add(apuesta2);
			contentPane.add(apuesta3);
			contentPane.add(apuesta4);
			contentPane.add(apuesta5);

			break;
		case 6:
			avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
			contentPane.add(avatar1);
			avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
			contentPane.add(avatar2);
			avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
			contentPane.add(avatar3);
			avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
			contentPane.add(avatar4);
			avatar5.setIcon(new ImageIcon(listaJugadores.get(4).get(3)));
			contentPane.add(avatar5);
			avatar6.setIcon(new ImageIcon(listaJugadores.get(5).get(3)));
			contentPane.add(avatar6);

			contentPane.add(dinero1);
			contentPane.add(fichadinero1);
			contentPane.add(dinero2);
			contentPane.add(fichadinero2);
			contentPane.add(dinero3);
			contentPane.add(fichadinero3);
			contentPane.add(dinero4);
			contentPane.add(fichadinero4);
			contentPane.add(dinero5);
			contentPane.add(fichadinero5);
			contentPane.add(dinero6);
			contentPane.add(fichadinero6);

			contentPane.add(apuesta1);
			contentPane.add(apuesta2);
			contentPane.add(apuesta3);
			contentPane.add(apuesta4);
			contentPane.add(apuesta5);
			contentPane.add(apuesta6);
			break;

		}

	}

	/**
	 * Muestra la ficha que muestra su capital directamente en el jugador que
	 * inicio sesion
	 */
	public void setFichaJugador() {
		switch (idJugador) {
		case 1:
			ficha.setBounds(50, 190, 160, 220);
			break;
		case 6:
			ficha.setBounds(1150, 190, 160, 220);
			break;
		case 2:
			ficha.setBounds(100, 450, 160, 220);
			break;
		case 5:
			ficha.setBounds(1100, 450, 160, 220);
			break;
		case 3:
			ficha.setBounds(440, 566, 160, 220);
			break;
		case 4:
			ficha.setBounds(850, 566, 160, 220);
			break;
		}
	}

	/**
	 * llena la lista de cartas de todos los jugadores en la interfaz pero no
	 * las muestra
	 */
	public void cartasJugadores() {

		carta11 = new JLabel("");
		carta11.setBounds(170, 200, 80, 100);

		carta12 = new JLabel("");
		carta12.setBounds(250, 200, 80, 100);

		carta61 = new JLabel("");
		carta61.setBounds(1030, 200, 80, 100);

		carta62 = new JLabel("");
		carta62.setBounds(1100, 200, 80, 100);

		carta21 = new JLabel("");
		carta21.setBounds(250, 500, 80, 100);

		carta22 = new JLabel("");
		carta22.setBounds(320, 500, 80, 100);

		carta51 = new JLabel("");
		carta51.setBounds(1000, 500, 80, 100);

		carta52 = new JLabel("");
		carta52.setBounds(1070, 500, 80, 100);

		carta31 = new JLabel("");
		carta31.setBounds(530, 570, 80, 100);

		carta32 = new JLabel("");
		carta32.setBounds(590, 570, 80, 100);

		carta41 = new JLabel("");
		carta41.setBounds(740, 570, 80, 100);

		carta42 = new JLabel("");
		carta42.setBounds(800, 570, 80, 100);

		ImageIcon iic = new ImageIcon("reverso.png");
		Icon ic = new ImageIcon(iic.getImage().getScaledInstance(
				carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));

		carta11.setIcon(ic);
		carta12.setIcon(ic);
		carta21.setIcon(ic);
		carta22.setIcon(ic);
		carta31.setIcon(ic);
		carta32.setIcon(ic);
		carta41.setIcon(ic);
		carta42.setIcon(ic);
		carta51.setIcon(ic);
		carta52.setIcon(ic);
		carta61.setIcon(ic);
		carta62.setIcon(ic);

		switch (listaJugadores.size()) {

		case 2:

			contentPane.add(carta11);
			contentPane.add(carta12);
			contentPane.add(carta21);
			contentPane.add(carta22);
			break;
		case 3:
			contentPane.add(carta11);
			contentPane.add(carta12);
			contentPane.add(carta21);
			contentPane.add(carta22);
			contentPane.add(carta31);
			contentPane.add(carta32);

			break;
		case 4:
			contentPane.add(carta11);
			contentPane.add(carta12);
			contentPane.add(carta21);
			contentPane.add(carta22);
			contentPane.add(carta31);
			contentPane.add(carta32);
			contentPane.add(carta41);
			contentPane.add(carta42);
		case 5:
			contentPane.add(carta11);
			contentPane.add(carta12);
			contentPane.add(carta21);
			contentPane.add(carta22);
			contentPane.add(carta31);
			contentPane.add(carta32);
			contentPane.add(carta41);
			contentPane.add(carta42);
			contentPane.add(carta51);
			contentPane.add(carta52);
			break;
		case 6:

			contentPane.add(carta11);
			contentPane.add(carta12);
			contentPane.add(carta21);
			contentPane.add(carta22);
			contentPane.add(carta31);
			contentPane.add(carta32);
			contentPane.add(carta41);
			contentPane.add(carta42);
			contentPane.add(carta51);
			contentPane.add(carta52);
			contentPane.add(carta61);
			contentPane.add(carta62);
			break;

		}

	}
	/**
	 * obtiene las cartas de la mesa
	 * @return cartas de la mesa
	 */
	public List<String> getCartas() {
		return cartas;
	}
	/**
	 * Establece una lista de cartas a la mesa
	 * @param cartas1 lista de cartas a establecer
	 */
	public void setCartas(List<String> cartas1) {
		this.cartas = cartas1;
	}

	/**
	 * muestra las cartas del jugador dueño de la sesión
	 */
	public void mostrarCartasJugador() {
		mostrarCartas(idJugador);
	}

/**
 * Muestra las cartas un jugador
 * @param jugador id del jugador 
 */
	public void mostrarCartas(int jugador) {
		ImageIcon iic;
		Icon ic;
		switch (jugador) {
		case 1:
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(5) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta11.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(6) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta12.setIcon(ic);
			break;
		case 2:
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(7) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta21.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(8) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta22.setIcon(ic);
			break;
		case 3:
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(9) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta31.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(10) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta32.setIcon(ic);
			break;
		case 4:
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(11) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta41.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(12) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta42.setIcon(ic);
			break;
		case 5:
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(13) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta51.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(14) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta52.setIcon(ic);
			break;
		case 6:
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(15) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta61.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-" + estiloCarta + "/"
					+ cartas.get(16) + ".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(
					carta11.getWidth(), carta11.getHeight(),
					Image.SCALE_DEFAULT));
			carta62.setIcon(ic);
			break;
		}
	}
/**
 * Hablilita los botones de apuestas para el jugador en turno
 * @param turno id del jugador que tiene el tueno
 * @param minApuesta	minima apuesta de la ronda
 */
	public void turno(int turno, int minApuesta) {

		if (turno == idJugador) {
			apostarBtn1.setEnabled(true);
			apostarBtn2.setEnabled(true);
			apostarBtn3.setEnabled(true);
			minApuesta(turno, minApuesta);
		} else {
			apostarBtn1.setEnabled(false);
			apostarBtn2.setEnabled(false);
			apostarBtn3.setEnabled(false);
		}

	}
	/**
	 * establece la minima y maxima apuesta del deslizador con los valores que puede apostar el jugador
	 * @param jugador jugador en turno
	 * @param minApuesta apuesta minima para la ronda
	 */
	public void minApuesta(int jugador, int minApuesta) {
		int apuesta = Integer.parseInt(apuestaJugadores.get(jugador - 1)
				.getText().trim());
		System.out.println("apuesta " + apuesta + " min " + minApuesta);

		slider.setMinimum(minApuesta - apuesta);
		if (minApuesta - apuesta != 0) {
			apostarBtn2.setEnabled(false);
		}
		textCantidad.setText(minApuesta - apuesta + "");
		slider.setValue(minApuesta - apuesta);
		apuesta = Integer.parseInt(dineroJugadores.get(jugador - 1).getText()
				.trim());
		slider.setMaximum(apuesta);
	}
	/**
	 * descuenta la cantidad de dinero del jugador indicado en la interfaz
	 * @param jugador id del jugador al que se le descuenta el dinero
	 * @param cantidad cantidad de dinero a descontar
	 */
	public void descontar(int jugador, int cantidad) {
		int dinero = Integer.parseInt(dineroJugadores.get(jugador - 1)
				.getText().trim());
		dinero -= cantidad;
		dineroJugadores.get(jugador - 1).setText(dinero + "");
		dinero = Integer.parseInt(apuestaJugadores.get(jugador - 1).getText()
				.trim());
		dinero += cantidad;
		apuestaJugadores.get(jugador - 1).setText(dinero + "");
		int pozo = Integer.parseInt(lblPozoCant.getText().trim());
		pozo += cantidad;
		lblPozoCant.setText(pozo + "");
	}
	/**
	 * establece un ganador para la mano
	 * @param ganador id fel jugador ganador
	 */
	public void ganador(int ganador) {
		final int ganador1 = ganador;
		lblGanador.setText("GANADOR JUGADOR" + ganador1);
		new Thread(new Runnable() {
			/**
			 * corre un hilo para esperar 5 segundos
			 */
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblGanador.setText("");
			}
		}).start();
		int dinero = Integer.parseInt(dineroJugadores.get(ganador - 1)
				.getText().trim());
		dinero += Integer.parseInt(lblPozoCant.getText().trim());
		dineroJugadores.get(ganador - 1).setText(dinero + "");
		lblPozoCant.setText(0 + "");
		iniciarCartas();
		cartasJugadores();

	}
	/**
	 * cambia las apuestas de los jugadores en la ronda a 0
	 */
	public void cambioRonda() {
		for (JLabel l : apuestaJugadores) {
			l.setText(0 + "");

		}

	}
	/**
	 * muestra las de los jugadores participantes en la mano a todos los jugadores
	 * @param participantes lista de identificadores de los jugadores participantes
	 */
	public void mostrarCartasParticipantes(List<Integer> participantes) {
		for (int i : participantes) {
			mostrarCartas(i);
		}
	}
	/**
	 * obtiene la instancia de la clase MesaJuego
	 * @return instancia de la mesa de juego
	 */
	private MesaJuego getClase() {
		return this;
	}
}
