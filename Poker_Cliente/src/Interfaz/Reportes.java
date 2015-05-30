package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexion.RMI;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reportes extends JFrame {

	private JPanel contentPane;
	JTable table;
	Administrador administrador;
	/**
	 * Create the frame.
	 * @param rmi 
	 * @param administrador 
	 */
	public Reportes(RMI rmi, Administrador administrador1) {
		administrador=administrador1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		CrearTabla(rmi);
	}
	/**
	 * @param rmi
	 */
	private void CrearTabla(final RMI rmi) {
		table = new JTable();
		table.setBounds(76, 28, 377, 259);
		contentPane.add(table);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("jugador");
		modelo.addColumn("motivo");
		modelo.addColumn("descripcion");
		modelo.addColumn("leido");
		
		
		List<List<String>> lj=null;
		try {
			lj=rmi.getReportes();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(List<String> ls : lj){
			
			Object[] object = new Object[3];
			object[0] = ls.get(0) ;
			object[1] = ls.get(1) ;
			object[2] = ls.get(2) ;
			
			modelo.addRow(object);
		}
	     table.setModel(modelo);
	     
	     JButton btnCerrar = new JButton("cerrar");
	     btnCerrar.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		setVisible(false);
	     		administrador.setVisible(true);
	     	}
	     });
	     btnCerrar.setBounds(213, 298, 89, 23);
	     contentPane.add(btnCerrar);
	}

}
