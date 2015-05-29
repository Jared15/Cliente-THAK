package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexion.RMI;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.List;

public class Administrador extends JFrame {

	private JPanel contentPane;
	JTable table;



	/**
	 * Create the frame.
	 * @param rmi 
	 */
	public Administrador(final RMI rmi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		CrearTabla(rmi);
		
		JButton btnEliminarJugador = new JButton("Eliminar Jugador");
		btnEliminarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre=(String) table.getValueAt(table.getSelectedRow(), 0);
				try {
					rmi.eliminarJugador(nombre);
					JOptionPane.showMessageDialog(null, "Usuario borrado correctamente");
					CrearTabla(rmi);
				} catch (RemoteException e) {					
					e.printStackTrace();
				}
				
			}
		});
		btnEliminarJugador.setBounds(10, 298, 130, 23);
		contentPane.add(btnEliminarJugador);
		
		JButton btnBloquearJugador = new JButton("Bloquear Jugador");
		btnBloquearJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre=(String) table.getValueAt(table.getSelectedRow(), 0);
				try {
					rmi.bloquearJugador(nombre);
					JOptionPane.showMessageDialog(null, "Usuario Bloqueado Correctamente");
					CrearTabla(rmi);
				} catch (RemoteException e) {					
					e.printStackTrace();
				}
			}
		});
		btnBloquearJugador.setBounds(150, 298, 138, 23);
		contentPane.add(btnBloquearJugador);
		
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("guis/guiAzul.png"));
		fondo.setBounds(5, 5, 521, 340);
		contentPane.add(fondo);
	}



	/**
	 * @param rmi
	 */
	private void CrearTabla(final RMI rmi) {
		table = new JTable();
		table.setBounds(76, 28, 377, 259);
		contentPane.add(table);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Nombre");
		modelo.addColumn("clave");
		modelo.addColumn("Puntos");
		modelo.addColumn("Partidas Ganadas");
		
		
		List<List<String>> lj=null;
		try {
			lj = rmi.listaAdmin();
		} catch (RemoteException e) {			
			e.printStackTrace();
		}
		for(List<String> ls : lj){
			
			Object[] object = new Object[4];
			object[0] = ls.get(0) ;
			object[1] = ls.get(1) ;
			object[2] = ls.get(2) ;
			object[3] = ls.get(3) ;
			
			modelo.addRow(object);
		}
	     table.setModel(modelo);
	}
}
