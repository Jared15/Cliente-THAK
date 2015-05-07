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

public class juego extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel carta1;
	private JLabel carta2;
	private JLabel carta3;
	private JLabel carta4;
	private JLabel carta5;
	private JLabel fondo;
	private String path;
	private List<String> cartas= new ArrayList<String>();;
	
	
	JLabel avatar1;
	JLabel avatar2;
	JLabel avatar3;
	JLabel avatar4;
	JLabel avatar5;
	JLabel avatar6;
	
	JLabel ficha;
	
	int idJugador;
	
	
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
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 * @throws RemoteException 
	 */
	public juego(RMI rmi1,String nu1) throws RemoteException {	
		rmi=rmi1;
		this.nu=nu1;
		
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
		btnSalir.setBounds(23, 24, 117, 25);
		panel.add(btnSalir);
		
		JButton btnEmpezar = new JButton("Primeras 3");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			cambiarCartas();
			   
			}
		});
		btnEmpezar.setBounds(23, 60, 117, 25);
		panel.add(btnEmpezar);
		
		JButton btnCuarta = new JButton("Cuarta");
		btnCuarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			   cuarta();
			}
		});
		btnCuarta.setBounds(23, 100, 117, 25);
		panel.add(btnCuarta);
		
		JButton btnQuinta = new JButton("Quinta");
		btnQuinta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			   quinta();
			}
		});
		btnQuinta.setBounds(23, 140, 117, 25);
		panel.add(btnQuinta);
		
		
		
		
		JLabel dealer = new JLabel("");
		dealer.setIcon(new ImageIcon("dealer1.png"));
		dealer.setBounds(676, 39, 208, 199);
		panel.add(dealer);
		
		
		ficha = new JLabel("");
		ficha.setIcon(new ImageIcon("ficharoja2.png"));
		panel.add(ficha);
		
		
		
		avatar1 = new JLabel("");
		avatar1.setBounds(50, 290, 160, 220);
		panel.add(avatar1);
		
		avatar2 = new JLabel("");
		avatar2.setBounds(1270, 290, 160, 220);
		panel.add(avatar2);

		avatar3 = new JLabel("");
		avatar3.setBounds(100, 550,160, 220);
		panel.add(avatar3);
		
		avatar4 = new JLabel("");
		avatar4.setBounds(1200, 550, 160, 220);
		panel.add(avatar4);
		
		avatar5 = new JLabel("");
		avatar5.setBounds(450, 666, 160, 220);
		panel.add(avatar5);
		
		avatar6 = new JLabel("");
		avatar6.setBounds(850, 666, 160, 220);
		panel.add(avatar6);
		
		
		
		llenarAvatares();
		cartasJugadores();
		System.out.println("IDJUGADOR------------------------------"+idJugador);
		setFichaJugador();
		
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
					rmi.getListaCartasMesa();
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		btnEmpezar_1.setBounds(23, 177, 117, 25);
		panel.add(btnEmpezar_1);
		fondo = new JLabel("");
		panel.add(fondo);
		
		fondo.setBounds(0,0, 1500, 1000);
		

	}
	
	
	/**
	 * muestra las primeras 3 cartas del juego en la mesa
	 */
	public void cambiarCartas(){
		c = new ImageIcon(cartas.get(0));

		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta1.setIcon(icono);
		c = new ImageIcon(cartas.get(1));
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta2.setIcon(icono);
		c = new ImageIcon(cartas.get(2));
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta3.setIcon(icono);
	}
	
	/**
	 * muestra la 4ta carta del juego en la mesa
	 */
	public void cuarta(){
		c = new ImageIcon(cartas.get(3));
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta4.setIcon(icono);
	}
	
	
	/**
	 * muestra la 5ta carta del juego en la mesa
	 */
	public void quinta(){

		c = new ImageIcon(cartas.get(4));
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
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				break;
			case 3:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				
				break;
			case 4:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
				break;
			case 5:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
				avatar5.setIcon(new ImageIcon(listaJugadores.get(4).get(3)));
				break;
			case 6:
				avatar1.setIcon(new ImageIcon(listaJugadores.get(0).get(3)));
				avatar2.setIcon(new ImageIcon(listaJugadores.get(1).get(3)));
				avatar3.setIcon(new ImageIcon(listaJugadores.get(2).get(3)));
				avatar4.setIcon(new ImageIcon(listaJugadores.get(3).get(3)));
				avatar5.setIcon(new ImageIcon(listaJugadores.get(4).get(3)));
				avatar6.setIcon(new ImageIcon(listaJugadores.get(5).get(3)));
				break;
			
		}
		
	}
	
	/**
	 * Muestra la ficha que muestra su capital directamente en el jugador que inicio sesion 
	 */
	public void setFichaJugador(){
		switch(idJugador){
		case 1:
			ficha.setBounds(50+80, 290+140, 91, 92);
			break;
		case 2:
			ficha.setBounds(1270+80, 290+140, 91, 92);
			break;
		case 3:
			ficha.setBounds(100+80, 550+140, 91, 92);
			break;
		case 4:
			ficha.setBounds(1200+80, 550+140, 91, 92);
			break;
		case 5:
			ficha.setBounds(450+80, 666+140, 91, 92);
			break;
		case 6:
			ficha.setBounds(850+80, 666+140, 91, 92);
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
		
		carta21 = new JLabel("");
		carta21.setBounds(1150, 300, 80, 100);
		
		carta22 = new JLabel("");
		carta22.setBounds(1220, 300, 80, 100);
		
		carta31 = new JLabel("");
		carta31.setBounds(250, 600, 80, 100);
		
		carta32 = new JLabel("");
		carta32.setBounds(320, 600, 80, 100);
		
		carta41 = new JLabel("");
		carta41.setBounds(1090, 600, 80, 100);
		
		carta42 = new JLabel("");
		carta42.setBounds(1150, 600, 80, 100);
		
		carta51 = new JLabel("");
		carta51.setBounds(560, 600, 80, 100);
		
		carta52 = new JLabel("");
		carta52.setBounds(620, 600, 80, 100);
		
		carta61 = new JLabel("");
		carta61.setBounds(740, 600, 80, 100);
		
		carta62 = new JLabel("");
		carta62.setBounds(800, 600, 80, 100);
		
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
	 * muestra las cartas del jugador dueno de la sesion
	 */
	public void mostrarCartasJugador(){
		ImageIcon iic;
		Icon ic ;
		
		switch(idJugador){
		case 1:
			iic = new ImageIcon(cartas.get(5));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta11.setIcon(ic);
			iic = new ImageIcon(cartas.get(6));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta12.setIcon(ic);
			break;
		case 2:
			iic = new ImageIcon(cartas.get(7));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta21.setIcon(ic);
			iic = new ImageIcon(cartas.get(8));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta22.setIcon(ic);
			break;
		case 3:
			iic = new ImageIcon(cartas.get(9));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta31.setIcon(ic);
			iic = new ImageIcon(cartas.get(10));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta32.setIcon(ic);
			break;
		case 4:
			iic = new ImageIcon(cartas.get(11));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta41.setIcon(ic);
			iic = new ImageIcon(cartas.get(12));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta42.setIcon(ic);
			break;
		case 5:
			iic = new ImageIcon(cartas.get(13));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta51.setIcon(ic);
			iic = new ImageIcon(cartas.get(14));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta52.setIcon(ic);
			break;
		case 6:
			iic = new ImageIcon(cartas.get(15));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta61.setIcon(ic);
			iic = new ImageIcon(cartas.get(16));
			ic = new ImageIcon(iic.getImage().getScaledInstance(carta11.getWidth(), carta11.getHeight(), Image.SCALE_DEFAULT));
			carta62.setIcon(ic);
			break;
		}
	}
}


