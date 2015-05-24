package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

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

public class MesaJuego extends JFrame {
	
	JLabel ficha;

	private JPanel contentPane;
	private JPanel panel;
	private JLabel carta1;
	private JLabel carta2;
	private JLabel carta3;
	private JLabel carta4;
	private JLabel carta5;
	private JLabel fondo;
	private String path;
	private List<String> cartas= new ArrayList<String>();
	
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
	
	private List<JLabel> dineroJugadores=new ArrayList<JLabel>();
	private List<JLabel> apuestaJugadores=new ArrayList<JLabel>();
	
	
	public int getEstiloCarta() {
		return estiloCarta;
	}

	public void setEstiloCarta(int estiloCarta) {
		this.estiloCarta = estiloCarta;
	}

	RMI rmi;
	List<String> mesa= new ArrayList<String>();
	List<List<String>> listaJugadores;
	String nu;

	public List<String> getMesa() {
		return mesa;
	}

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
	private JButton btnEmpezar_1;
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
	
	
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 * @throws RemoteException 
	 */
	public MesaJuego(RMI rmi1,String nu1) throws RemoteException {	
		
		
		rmi=rmi1;
		this.nu=nu1;
		
		rmi.llenarMazo(1);
		listaJugadores= rmi.getListaJugadores();
		
		for(int j=0;j<listaJugadores.size();j++){
			if(listaJugadores.get(j).get(0).equalsIgnoreCase(nu)){
				idJugador=j+1;
			}			
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1489, 999);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(Color.decode("#3b0707"));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			   
			}
		});
		textCantidad = new JTextField();
		textCantidad.setFont(new Font("Calibri", Font.BOLD, 17));
		textCantidad.setForeground(SystemColor.text);
		textCantidad.setBackground(SystemColor.desktop);
		textCantidad.setBounds(688, 578, 80, 40);
		panel.add(textCantidad);
		textCantidad.setColumns(10);
		
		slider = new JSlider();
		slider.setBackground(SystemColor.desktop);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				textCantidad.setText(slider.getValue()+"");
			}
		});
		slider.setMinimum(50);
		slider.setMaximum(200);
		slider.setBounds(622, 619, 216, 25);
		panel.add(slider);
		
		fondoApuestas = new JLabel("");
		fondoApuestas.setIcon(new ImageIcon("Botones/apuestas.png"));
		fondoApuestas.setBounds(615, 565, 235, 90);
		panel.add(fondoApuestas);
		
		
		
		lblGanador = new JLabel("Ganador");
		lblGanador.setForeground(SystemColor.text);
		lblGanador.setFont(new Font("Calibri", Font.BOLD, 71));
		lblGanador.setBounds(395, 219, 865, 76);
		panel.add(lblGanador);
		lblGanador.setVisible(false);
		btnSalir.setBounds(23, 24, 117, 25);
		panel.add(btnSalir);
		
		JButton btnEmpezar = new JButton("Primeras 3");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.mostrarPrimeras3();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			   
			}
		});
		btnEmpezar.setBounds(23, 60, 117, 25);
		panel.add(btnEmpezar);
		
		JButton btnCuarta = new JButton("Cuarta");
		btnCuarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.mostrarCuarta();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCuarta.setBounds(23, 100, 117, 25);
		panel.add(btnCuarta);
		
		JButton btnQuinta = new JButton("Quinta");
		btnQuinta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.mostrarQuinta();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnQuinta.setBounds(23, 140, 117, 25);
		panel.add(btnQuinta);
		
		apuesta1 = new JLabel("0");
		apuesta1.setForeground(SystemColor.text);
		apuesta1.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta1.setBounds(313, 402, 39, 35);
		
		apuestaJugadores.add(apuesta1);
		
		apuesta2= new JLabel("0");		
		apuesta2.setForeground(SystemColor.text);
		apuesta2.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta2.setBounds(375+70, 583, 46, 35);
		apuestaJugadores.add(apuesta2);
		
		apuesta3= new JLabel("0");		
		apuesta3.setForeground(SystemColor.text);
		apuesta3.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta3.setBounds(622-50, 630, 46, 40);
		apuestaJugadores.add(apuesta3);
		
		apuesta4 = new JLabel("0");
		apuesta4.setForeground(SystemColor.text);
		apuesta4.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta4.setBounds(850, 630, 46, 35);
		apuestaJugadores.add(apuesta4);
		
		apuesta5= new JLabel("0");		
		apuesta5.setForeground(SystemColor.text);
		apuesta5.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta5.setBounds(1056, 583, 46, 25);
		apuestaJugadores.add(apuesta5);
		
		apuesta6= new JLabel("0");		
		apuesta6.setForeground(SystemColor.text);
		apuesta6.setFont(new Font("Calibri", Font.BOLD, 25));
		apuesta6.setBounds(1085, 407, 46, 25);
		apuestaJugadores.add(apuesta6);
		
		
		
		
		JLabel dealer = new JLabel("");
		dealer.setIcon(new ImageIcon("dealer1.png"));
		dealer.setBounds(676, 39, 208, 199);
		panel.add(dealer);
		
		ficha = new JLabel("");
		ficha.setIcon(new ImageIcon("avatares/halo.png"));
		panel.add(ficha);
		setFichaJugador();
		
		avatar1 = new JLabel("");
		avatar1.setBounds(50, 290, 160, 220);
		
		avatar2 = new JLabel("");
		avatar2.setBounds(100, 550, 160, 220);

		avatar3 = new JLabel("");
		avatar3.setBounds(450, 666,160, 220);
		
		avatar4 = new JLabel("");
		avatar4.setBounds(850, 666, 160, 220);
		
		avatar5 = new JLabel("");
		avatar5.setBounds(1200, 550, 160, 220);
		
		avatar6 = new JLabel("");
		avatar6.setBounds(1270, 290, 160, 220);
		
		
		
		
		dinero1 = new JLabel("0");
		dinero1.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero1.setBounds(174+70, 479, 40, 34);
		
		dineroJugadores.add(dinero1);
		
		dinero2= new JLabel("0");		
		dinero2.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero2.setBounds(244+70, 734, 40, 34);
		dineroJugadores.add(dinero2);
		
		dinero3= new JLabel("0");		
		dinero3.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero3.setBounds(579+70, 848, 40, 34);
		dineroJugadores.add(dinero3);
		
		dinero4 = new JLabel("0");
		dinero4.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero4.setBounds(852-70, 844, 50, 40);
		dineroJugadores.add(dinero4);
		
		dinero5= new JLabel("0");		
		dinero5.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero5.setBounds(1188-70, 730, 50, 40);
		dineroJugadores.add(dinero5);
		
		dinero6= new JLabel("0");		
		dinero6.setFont(new Font("Calibri", Font.BOLD, 14));
		dinero6.setBounds(1256-70, 481, 50, 31);
		dineroJugadores.add(dinero6);
		
		fichadinero1 = new JLabel("");
		fichadinero1.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero1.setBounds(144+70, 448, 100, 100);
		
		fichadinero2 = new JLabel("");
		fichadinero2.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero2.setBounds(214+70, 701, 100, 100);
		
		fichadinero3 = new JLabel("");
		fichadinero3.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero3.setBounds(550+70, 814, 100, 100);
		
		fichadinero4 = new JLabel("");
		fichadinero4.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero4.setBounds(821-70, 814, 100, 100);
		
		fichadinero5 = new JLabel("");
		fichadinero5.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero5.setBounds(1160-70, 701, 100, 100);
		
		fichadinero6 = new JLabel("");
		fichadinero6.setIcon(new ImageIcon("ficharoja2.png"));
		fichadinero6.setBounds(1228-70, 448, 100, 100);
		
		
		

		
		
		llenarAvatares();
		cartasJugadores();
		System.out.println("IDJUGADOR------------------------------"+idJugador);
		
		
		carta1 = new JLabel("");
		carta1.setBounds(500, 400, 80, 100);
		ImageIcon c1 = new ImageIcon("reverso.png");
		Icon icono1 = new ImageIcon(c1.getImage().getScaledInstance(carta1.getWidth(), carta1.getHeight(), Image.SCALE_DEFAULT));
		
		
		
		
		carta1.setIcon(icono1);
		panel.add(carta1);
		
		
		carta2 = new JLabel("");
		carta2.setBounds(600, 400, 80, 100);
		ImageIcon c2 = new ImageIcon("reverso.png");
		Icon icono2 = new ImageIcon(c2.getImage().getScaledInstance(carta2.getWidth(), carta2.getHeight(), Image.SCALE_DEFAULT));
		carta2.setIcon(icono2);
		panel.add(carta2);
		
		carta3 = new JLabel("");
		carta3.setBounds(700, 400, 80, 100);
		ImageIcon c3 = new ImageIcon("reverso.png");
		Icon icono3 = new ImageIcon(c3.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta3.setIcon(icono3);
		panel.add(carta3);
		
		carta4 = new JLabel("");
		carta4.setBounds(800, 400, 80, 100);
		ImageIcon c4 = new ImageIcon("reverso.png");
		Icon icono4 = new ImageIcon(c4.getImage().getScaledInstance(carta4.getWidth(), carta4.getHeight(), Image.SCALE_DEFAULT));
		carta4.setIcon(icono4);
		panel.add(carta4);
		
		carta5 = new JLabel("");
		carta5.setBounds(900, 400, 80, 100);
		ImageIcon c5 = new ImageIcon("reverso.png");
		Icon icono5 = new ImageIcon(c5.getImage().getScaledInstance(carta5.getWidth(), carta5.getHeight(), Image.SCALE_DEFAULT));
		carta5.setIcon(icono5);
		panel.add(carta5);

		btnEmpezar_1 = new JButton("Empezar");
		btnEmpezar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.llenarMazo(1);
					rmi.getListaCartasMesa();
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		//BOTONES APOSTAR
		apostarBtn1 = new JButton();
		apostarBtn1.setBackground(new Color(0, 0, 0));
		apostarBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					rmi.retirarse(idJugador);;				
				} catch (RemoteException e) {
					e.printStackTrace();
				}	
			}
		});
		apostarBtn1.setBounds(639, 513, 50, 50);
		apostarBtn1.setIcon(new ImageIcon("Botones/retirarse.png"));
		panel.add(apostarBtn1);
		
		
		apostarBtn2 = new JButton();
		apostarBtn2.setBackground(new Color(0, 0, 0));
		apostarBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					rmi.apostar(idJugador,0);				
				} catch (RemoteException e) {
					e.printStackTrace();
				}	
			}
		});
		apostarBtn2.setBounds(708, 513, 50, 50);
		apostarBtn2.setIcon(new ImageIcon("Botones/pasar.png"));
		panel.add(apostarBtn2);
		
		apostarBtn3 = new JButton();
		apostarBtn3.setBackground(new Color(0, 0, 0));
		apostarBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dinero=Integer.parseInt(textCantidad.getText().trim());
				int dineroJug=Integer.parseInt(dineroJugadores.get(idJugador-1).getText().trim());
				if(dinero<=dineroJug){
					try {					
						rmi.apostar(idJugador,dinero);					
					} catch (RemoteException e) {
						e.printStackTrace();
					}	
				}else{
					JOptionPane.showMessageDialog(null,"Dinero Insuficiente!");
				}
			}
		});
		apostarBtn3.setBounds(770, 513, 50, 50);
		apostarBtn3.setIcon(new ImageIcon("Botones/subir.png"));
		panel.add(apostarBtn3);

		
		
		btnEmpezar_1.setBounds(23, 177, 117, 25);
		panel.add(btnEmpezar_1);
		
		JButton btnSigRonda = new JButton("Sig Ronda");
		btnSigRonda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rmi.siguienteRonda();
				} catch (RemoteException e) {					
					e.printStackTrace();
				}
			}
		});
		btnSigRonda.setBounds(143, 24, 117, 23);
		panel.add(btnSigRonda);
		
		JButton btnSiguienteMano = new JButton("siguiente mano");
		btnSiguienteMano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lblGanador.setVisible(false);
					rmi.llenarMazo(1);
					rmi.getListaCartasMesa();					
				} catch (RemoteException e) {					
					e.printStackTrace();
				}
			}

			
		});
		btnSiguienteMano.setBounds(143, 61, 117, 23);
		panel.add(btnSiguienteMano);
		fondo = new JLabel("");
		fondo.setBackground(new Color(128, 0, 0));
		panel.add(fondo);
		
		fondo.setBounds(0,11, 1500, 1000);
		
		llenarDinero();
		for(int j=0;j<listaJugadores.size();j++){			
			dineroJugadores.get(j).setText(listaJugadores.get(j).get(1));
		}
			
		
	}
	
	private void iniciarCartas() {
		c =new ImageIcon("reverso.png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta1.setIcon(icono);
		carta2.setIcon(icono);
		carta3.setIcon(icono);
		carta4.setIcon(icono);
		carta5.setIcon(icono);
		
	}

	/**
	 * 
	 */
	private void llenarDinero() {
		
	}
	
	
	/**
	 * muestra las primeras 3 cartas del juego en la mesa
	 */
	public void cambiarCartas(){
		c = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(0)+".png");

		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta1.setIcon(icono);
		c = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(1)+".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta2.setIcon(icono);
		c = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(2)+".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta3.setIcon(icono);
	}
	
	/**
	 * muestra la 4ta carta del juego en la mesa
	 */
	public void cuarta(){
		c = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(3)+".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta4.setIcon(icono);
	}
	
	
	/**
	 * muestra la 5ta carta del juego en la mesa
	 */
	public void quinta(){

		c = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(4)+".png");
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta5.setIcon(icono);
		
	}
	/**
	 * Cambia el color de la mesa para el jugador
	 * @param path (direccion de la imagen que selecciono para la mesa 
	 */
	public void setColorMesa(String path){
		fondo.setIcon(new ImageIcon(path));
	}
	
	/**
	 * muestra las imagenes o avatares de los jugadores que ingresaron a la mesa 
	 */
	public void llenarAvatares(){
		
		switch(listaJugadores.size()){

			case 2:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				panel.add(avatar1);
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				panel.add(avatar2);
				panel.add(avatar2);
				panel.add(dinero1);
				panel.add(fichadinero1);
				panel.add(dinero2);
				panel.add(fichadinero2);
				
				panel.add(apuesta1);
				panel.add(apuesta2);
				break;
			case 3:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				panel.add(avatar1);
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				panel.add(avatar2);
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				panel.add(avatar3);
				
				panel.add(dinero1);
				panel.add(fichadinero1);
				panel.add(dinero2);
				panel.add(fichadinero2);
				panel.add(dinero3);
				panel.add(fichadinero3);
				
				panel.add(apuesta1);
				panel.add(apuesta2);
				panel.add(apuesta3);

				break;
			case 4:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				panel.add(avatar1);
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				panel.add(avatar2);
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				panel.add(avatar3);
				avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
				panel.add(avatar4);
				
				panel.add(dinero1);
				panel.add(fichadinero1);
				panel.add(dinero2);
				panel.add(fichadinero2);
				panel.add(dinero3);
				panel.add(fichadinero3);
				panel.add(dinero4);
				panel.add(fichadinero4);
				panel.add(apuesta1);
				panel.add(apuesta2);
				panel.add(apuesta3);
				panel.add(apuesta4);

				break;
			case 5:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				panel.add(avatar1);
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				panel.add(avatar2);
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				panel.add(avatar3);
				avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
				panel.add(avatar4);
				avatar5.setIcon(new ImageIcon(listaJugadores.get(4).get(3)));
				panel.add(avatar5);
				
				panel.add(dinero1);
				panel.add(fichadinero1);
				panel.add(dinero2);
				panel.add(fichadinero2);
				panel.add(dinero3);
				panel.add(fichadinero3);
				panel.add(dinero4);
				panel.add(fichadinero4);
				panel.add(dinero5);
				panel.add(fichadinero5);
				
				panel.add(apuesta1);
				panel.add(apuesta2);
				panel.add(apuesta3);
				panel.add(apuesta4);
				panel.add(apuesta5);
				
				break;
			case 6:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				panel.add(avatar1);
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				panel.add(avatar2);
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				panel.add(avatar3);
				avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
				panel.add(avatar4);
				avatar5.setIcon(new ImageIcon(listaJugadores.get(4).get(3)));
				panel.add(avatar5);
				avatar6.setIcon(new ImageIcon(listaJugadores.get(5).get(3)));
				panel.add(avatar6);
				
				
				panel.add(dinero1);
				panel.add(fichadinero1);
				panel.add(dinero2);
				panel.add(fichadinero2);
				panel.add(dinero3);
				panel.add(fichadinero3);
				panel.add(dinero4);
				panel.add(fichadinero4);
				panel.add(dinero5);
				panel.add(fichadinero5);
				panel.add(dinero6);
				panel.add(fichadinero6);
				
				
				panel.add(apuesta1);
				panel.add(apuesta2);
				panel.add(apuesta3);
				panel.add(apuesta4);
				panel.add(apuesta5);
				panel.add(apuesta6);
				break;
			
		}
		
	}
	
	/**
	 * Muestra la ficha que muestra su capital directamente en el jugador que inicio sesion 
	 */
	public void setFichaJugador(){
		switch(idJugador){
		case 1:
			ficha.setBounds(50, 290, 160, 220);
			break;
		case 6:
			ficha.setBounds(1270, 290, 160, 220);
			break;
		case 2:
			ficha.setBounds(100, 550, 160, 220);
			break;
		case 5:
			ficha.setBounds(1200, 550, 160, 220);
			break;
		case 3:
			ficha.setBounds(450, 666, 160, 220);
			break;
		case 4:
			ficha.setBounds(850, 666, 160, 220);
			break;
		}
	}
	
	
	/**
	 * llena la lista de cartas de todos los jugadores en la interfaz pero no las muestra
	 */
	public void cartasJugadores(){
		
		carta11 = new JLabel("");
		carta11.setBounds(170, 300, 80, 100);
		
		carta12 = new JLabel("");
		carta12.setBounds(250, 300, 80, 100);
		
		carta61 = new JLabel("");
		carta61.setBounds(1150, 300, 80, 100);
		
		carta62 = new JLabel("");
		carta62.setBounds(1220, 300, 80, 100);
		
		carta21 = new JLabel("");
		carta21.setBounds(250, 600, 80, 100);
		
		carta22 = new JLabel("");
		carta22.setBounds(320, 600, 80, 100);
		
		carta51 = new JLabel("");
		carta51.setBounds(1090, 600, 80, 100);
		
		carta52 = new JLabel("");
		carta52.setBounds(1150, 600, 80, 100);
		
		carta31 = new JLabel("");
		carta31.setBounds(560, 670, 80, 100);
		
		carta32 = new JLabel("");
		carta32.setBounds(620, 670, 80, 100);
		
		carta41 = new JLabel("");
		carta41.setBounds(740, 670, 80, 100);
		
		carta42 = new JLabel("");
		carta42.setBounds(800, 670, 80, 100);
		
		ImageIcon iic = new ImageIcon("reverso.png");
		Icon ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
		
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
		
		switch(listaJugadores.size()){

		case 2:

			panel.add(carta11);
			panel.add(carta12);
			panel.add(carta21);
			panel.add(carta22);
			break;
		case 3:
			panel.add(carta11);
			panel.add(carta12);
			panel.add(carta21);
			panel.add(carta22);
			panel.add(carta31);
			panel.add(carta32);
			
			break;
		case 4:
			panel.add(carta11);
			panel.add(carta12);
			panel.add(carta21);
			panel.add(carta22);
			panel.add(carta31);
			panel.add(carta32);
			panel.add(carta41);
			panel.add(carta42);
		case 5:
			panel.add(carta11);
			panel.add(carta12);
			panel.add(carta21);
			panel.add(carta22);
			panel.add(carta31);
			panel.add(carta32);
			panel.add(carta41);
			panel.add(carta42);
			panel.add(carta51);
			panel.add(carta52);
			break;
		case 6:
			
			panel.add(carta11);
			panel.add(carta12);
			panel.add(carta21);
			panel.add(carta22);
			panel.add(carta31);
			panel.add(carta32);
			panel.add(carta41);
			panel.add(carta42);
			panel.add(carta51);
			panel.add(carta52);
			panel.add(carta61);
			panel.add(carta62);
			break;
		
	}
		
	}

	public List<String> getCartas() {
		return cartas;
	}

	public void setCartas(List<String> cartas1) {		
		this.cartas = cartas1;
			}
	
	
	/**
	 * muestra las cartas del jugador dueño de la sesión
	 */
	public void mostrarCartasJugador(){
		ImageIcon iic;
		Icon ic ;
		
		switch(idJugador){
		case 1:
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(5)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta11.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(6)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta12.setIcon(ic);
			break;
		case 2:
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(7)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta21.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(8)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta22.setIcon(ic);
			break;
		case 3:
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(9)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta31.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(10)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta32.setIcon(ic);
			break;
		case 4:
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(11)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta41.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(12)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta42.setIcon(ic);
			break;
		case 5:
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(13)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta51.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(14)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta52.setIcon(ic);
			break;
		case 6:
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(15)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta61.setIcon(ic);
			iic = new ImageIcon("Cartas/Cartas-"+estiloCarta+"/"+cartas.get(16)+".png");
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta62.setIcon(ic);
			break;
		}
	}

	public void turno(int turno) {
		if(turno==idJugador){
			apostarBtn1.setEnabled(true);
			apostarBtn2.setEnabled(true);
			apostarBtn3.setEnabled(true);
		}else{
			apostarBtn1.setEnabled(false);
			apostarBtn2.setEnabled(false);
			apostarBtn3.setEnabled(false);
		}
		
	}

	public void descontar(int jugador, int cantidad) {
		int dinero=Integer.parseInt(dineroJugadores.get(jugador-1).getText().trim());
		dinero-=cantidad;
		dineroJugadores.get(jugador-1).setText(dinero+"");
		dinero=Integer.parseInt(apuestaJugadores.get(jugador-1).getText().trim());
		dinero+=cantidad;
		apuestaJugadores.get(jugador-1).setText(dinero+"");
		
	}

	public void ganador(int ganador) {
		lblGanador.setText("GANADOR jugador "+ganador);
		lblGanador.setVisible(true);
		
	}
}


