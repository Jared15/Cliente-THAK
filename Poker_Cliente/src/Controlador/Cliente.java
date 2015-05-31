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
	public Cliente() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;
	static IniciarSesion pokerInterfaz;
	static RMI remoteService;
	
	static Cliente client;
	/**
	 * 
	 * @param args parametros enviados desde la ejecucion, no son usados
	 */
	public static void main(String[] args) {
		try {
			remoteService = (RMI) Naming.lookup("//10.5.2.39:9999/RmiService");
			client = new Cliente();
			remoteService.addObserver(client);			
			pokerInterfaz = new IniciarSesion(remoteService,client);
			pokerInterfaz.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**


	 * es el que actualiza la interfaz grafica de la partido con el RMI con el
	 * patron Observer
	 * @param observable
	 * @param updateMsg lista de objetos con el identificador del estado del juego
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
					pokerInterfaz.getSesion().getCm().getJuego().turno(1,0);
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
				pokerInterfaz.getSesion().getCm().getJuego().cambioRonda();
				
				
			}else{
				switch(accion){
				case -3:
					if(pokerInterfaz.getSesion()!=null && pokerInterfaz.getSesion().getCm()!=null){
						pokerInterfaz.getSesion().getCm().actualizarJugadores();
					}					
					break;
				case -2:
					if(pokerInterfaz.getSesion()!=null && pokerInterfaz.getSesion().getCm()!=null ){
						pokerInterfaz.getSesion().getCm().iniciarPartida();
					}					
					break;
				case -1:
					//TODO mostrar cartas de los que estan participando cuando se termina una mano
					/*List<Integer> participando=(List<Integer>) ((List<Object>) updateMsg).get(2);
					pokerInterfaz.getSesion().getCm().getJuego().mostrarCartasParticipantes(participando);*/
					pokerInterfaz.ganador(state);				
					pokerInterfaz.getSesion().getCm().getJuego().cambioRonda();
					break;
				default:
					int turno=(int) ((List<Object>) updateMsg).get(2);						
					pokerInterfaz.getSesion().getCm().getJuego().turno(turno,(int) ((List<Object>) updateMsg).get(3));
					System.out.println(""+(int) ((List<Object>) updateMsg).get(4));//Ronda de apuestas
			
					
					if(state>0){
						pokerInterfaz.getSesion().getCm().getJuego().descontar(accion,state);
					}		
					break;
				}
					
			}
		}
	}
	public static RMI getRemoteService() {
		return remoteService;
	}


	public static void setRemoteService(RMI remoteService) {
		Cliente.remoteService = remoteService;
	}

	public static Cliente getClient() {
		return client;
	}


	public static void setClient(Cliente client) {
		Cliente.client = client;
	}

}