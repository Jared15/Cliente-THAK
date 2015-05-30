package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conexion.RMI;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jugadores extends JFrame {

	private JPanel contentPane;
	DefaultTableModel modelo;
	Object[] object;
	MesaJuego mesa;
	private JTable table;
	RMI rmi;
	/**
	 * Create the frame.
	 * @param rmi 
	 */
	public Jugadores(RMI rmi1,MesaJuego mesa) {
		this.rmi=rmi1;
		this.mesa=mesa;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#3b0707"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 33, 364, 341);
		contentPane.add(table);

		modelo = new DefaultTableModel();

		modelo.addColumn("Nombre");
		modelo.addColumn("Puntos");
		modelo.addColumn("Partidas Ganadas");
		List<List<String>> lj=null;
		try {
			lj = rmi.getListaJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (List<String> ls : lj) {

			object = new Object[3];
			object[0] = ls.get(0);
			object[1] = ls.get(1);
			object[2] = ls.get(2);

			modelo.addRow(object);
		}
		table.setModel(modelo);
		
		JButton btnVerDetalle = new JButton("ver detalle");
		btnVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre=(String) table.getValueAt(table.getSelectedRow(), 0);
				try {
					Detalles detalles=new Detalles(rmi,nombre,getClase());
					detalles.setVisible(true);
					setVisible(false);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		btnVerDetalle.setBounds(44, 387, 106, 23);
		contentPane.add(btnVerDetalle);
		
		JButton btnDenunciar = new JButton("denunciar");
		btnDenunciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=(String) table.getValueAt(table.getSelectedRow(), 0);
				Denuncio denuncio=new Denuncio(rmi, getClase(),nombre);
				denuncio.setVisible(true);
				setVisible(false);
			}
		});
		btnDenunciar.setBounds(203, 387, 106, 23);
		contentPane.add(btnDenunciar);
		
	}
	public Jugadores getClase() {
		return this;
	}
}
