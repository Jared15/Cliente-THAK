package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

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

import javax.swing.JScrollPane;

public class Administrador extends JFrame {

	private JPanel contentPane;
	JTable table;



	/**
	 * Crea el Frame
	 * @param rmi Interface con los metodos a utilizar para la conexion con el servidor 
	 */
	public Administrador(final RMI rmi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle("Administrador");
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
		btnEliminarJugador.setBounds(10, 298, 111, 23);
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
		btnBloquearJugador.setBounds(131, 298, 122, 23);
		contentPane.add(btnBloquearJugador);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double promedio=rmi.promedioGanadas();
					JOptionPane.showMessageDialog(null, "Promedio de partidas ganadas: "+promedio);
				} catch (RemoteException e) {					
					e.printStackTrace();
				}
			}
		});
		btnGenerarReporte.setBounds(263, 298, 122, 23);
		contentPane.add(btnGenerarReporte);
		
		JButton btnRevisarReportes = new JButton("Revisar Reportes");
		btnRevisarReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reportes reporte=new Reportes(rmi,getClase());
				reporte.setVisible(true);
				setVisible(false);
			}
		});
		btnRevisarReportes.setBounds(395, 298, 121, 23);
		contentPane.add(btnRevisarReportes);
		
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("guis/guiAzul.png"));
		fondo.setBounds(5, 5, 521, 340);
		contentPane.add(fondo);
	}



	/**
	 * Crea y llena la tabla "table" con su contenido
	 * @param rmi Interface con los metodos a utilizar para la conexion con el servidor 
	 */
	private void CrearTabla(final RMI rmi) {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 56, 377, 231);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
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
	
	/**
	 * devuelve la instacia que esta corriendo esta clase
	 * @return
	 */
	public Administrador getClase(){
		return this;
	}
}
