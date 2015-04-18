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

import Logica.Dealer;
import Logica.Juego;
import Manos.Carta;
import Manos.Palo;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class juego extends JFrame {

	private JPanel contentPane;
	private JLabel carta1;
	private JLabel carta2;
	private JLabel carta3;
	private JLabel carta4;
	private JLabel carta5;

	
	//Mesa mesa=new Mesa();
	ImageIcon c;
	Icon icono;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					juego frame = new juego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public juego() {	
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1489, 999);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
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
		
		JLabel avatar = new JLabel("");
		avatar.setIcon(new ImageIcon("homero.png"));
		avatar.setBounds(660, 666, 163, 219);
		panel.add(avatar);
		
		JLabel avatar1 = new JLabel("");
		avatar1.setBounds(200, 650, 170, 170);
		ImageIcon a1 = new ImageIcon("pokerface.png");
		Icon ic1 = new ImageIcon(a1.getImage().getScaledInstance(avatar1.getWidth(), avatar1.getHeight(), Image.SCALE_DEFAULT));
		avatar1.setIcon(ic1);
		panel.add(avatar1);
		
		JLabel avatar2 = new JLabel("");
		avatar2.setBounds(1100, 650, 200, 160);
		a1 = new ImageIcon("yoda.png");
		ic1 = new ImageIcon(a1.getImage().getScaledInstance(avatar2.getWidth(), avatar2.getHeight(), Image.SCALE_DEFAULT));
		avatar2.setIcon(ic1);
		panel.add(avatar2);
		
		
		JLabel ficha = new JLabel("");
		ficha.setIcon(new ImageIcon("ficharoja2.png"));
		ficha.setBounds(793, 687, 91, 92);
		panel.add(ficha);
		
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
		
		JLabel fondo = new JLabel("");
		panel.add(fondo);
		fondo.setIcon(new ImageIcon("mesaazul.png"));
		fondo.setBounds(0,0, 1500, 1000);
	}
	
	public void cambiarCartas(){
		c = new ImageIcon(//aqui va el metodo mesa.getPath() que se llama desde mesa);
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta1.setIcon(icono);
		c = new ImageIcon(//aqui va el metodo que se llama desde mesa);
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta2.setIcon(icono);
		c = new ImageIcon(//aqui va el metodo que se llama desde mesa);
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta3.setIcon(icono);
	}
	public void cuarta(){
		c = new ImageIcon(//aqui va el metodo que se llama desde mesa);
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta4.setIcon(icono);
	}
	
	public void quinta(){
		c = new ImageIcon(//aqui va el metodo  que se llama desde mesa);
		icono = new ImageIcon(c.getImage().getScaledInstance(carta3.getWidth(), carta3.getHeight(), Image.SCALE_DEFAULT));
		carta5.setIcon(icono);
	}
}
