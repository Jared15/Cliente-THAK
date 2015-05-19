package Controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import Conexion.RMI;
import Connection.RemoteObserver;
import Interfaz.IniciarSesion;

public class Cliente extends UnicastRemoteObject implements RemoteObserver {
	protected Cliente() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;
	static IniciarSesion pokerInterfaz;
	static RMI remoteService;

	public static void main(String[] args) {
		try {
			remoteService = (RMI) Naming.lookup("//localhost:9999/RmiService");
			Cliente client = new Cliente();
			remoteService.addObserver(client);
			pokerInterfaz = new IniciarSesion(remoteService);
			pokerInterfaz.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * es el que actualiza la interfaz grafica de la partido con el RMI con el
	 * patron Observer
	 */
	@Override
	public void update(Object observable, Object updateMsg)
			throws RemoteException {
		if (updateMsg != null) {

			int accion = (int) ((List<Object>) updateMsg).get(0);
			int state = (int) ((List<Object>) updateMsg).get(1);
			System.out.println("---*** ESTADO : " + state + " ***---");
			if (accion == 0) {				
				List<String> lj = new ArrayList<String>();
				switch (state) {
				case 0:
					lj = (List<String>) ((List<Object>) updateMsg).get(3);
					pokerInterfaz.getSesion().getCm().getJuego().setCartas(lj);
					pokerInterfaz.getSesion().getCm().getJuego().setVisible(true);
					pokerInterfaz.getSesion().getCm().getJuego().mostrarCartasJugador();
					pokerInterfaz.getSesion().getCm().getJuego().turno(1);
					break;
				case 1:
					pokerInterfaz.getSesion().getCm().getJuego().cambiarCartas();

					break;
				case 2:
					pokerInterfaz.getSesion().getCm().getJuego().cuarta();
					break;
				case 3:
					pokerInterfaz.getSesion().getCm().getJuego().quinta();
					break;
				}
			}else{
				if(accion==-1){
					pokerInterfaz.ganador(state);
				}else{
					int turno=(int) ((List<Object>) updateMsg).get(2);					
					pokerInterfaz.getSesion().getCm().getJuego().turno(turno);
					if(state>0){
						pokerInterfaz.getSesion().getCm().getJuego().descontar(accion,state);
					}			
				}					
			}
		}
	}
}