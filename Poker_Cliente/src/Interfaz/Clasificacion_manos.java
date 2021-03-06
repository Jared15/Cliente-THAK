package Interfaz;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class Clasificacion_manos extends JFrame {

	private JPanel contentPane;
	/**
	 * crea el frame con todo su contenido
	 */
	public Clasificacion_manos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle("Clasificacion de las manos ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.RED);
		btnSalir.addActionListener(new ActionListener() {
			/**
			 * boton que es llamado para ocultar el frame
			 */
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnSalir.setBounds(338, 211, 79, 49);
		contentPane.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 440, 265);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		JLabel etiqueta = new JLabel(); 
        
        Icon imagen = new ImageIcon ("manos.png"); 
        etiqueta.setIcon (new ImageIcon("manos.png")); 
        contentPane.add(scrollPane); 
        scrollPane.setViewportView(etiqueta); 
		
	}

}
