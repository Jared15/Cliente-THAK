package Controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import Conexion.RMI;
import Connection.RemoteObserver;
import Interfaz.PokerInterfaz;

public class Main extends UnicastRemoteObject implements RemoteObserver {
    protected Main() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;
    static PokerInterfaz pokerInterfaz;
    static RMI remoteService;

    public static void main(String[] args) {
                try {
            remoteService = (RMI) Naming.lookup("//localhost:9999/RmiService");
            Main client = new Main();
            remoteService.addObserver(client);
            pokerInterfaz = new PokerInterfaz(remoteService);
            pokerInterfaz.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
/**
 * es el que actualiza la interfaz grafica de la partido con el RMI con el patron Observer 
 */
    @Override
    public void update(Object observable, Object updateMsg) throws RemoteException {
    		System.out.println("UPDATE");
if(updateMsg!=null){
	int state= (int) ((List<Object>) updateMsg).get(0);
	System.out.println("---*** ESTADO : "+state+" ***---");

	
        List<String> lj=new ArrayList<String>(); 
      //  if(state==0){		
        
        lj= (List<String>)((List<Object>) updateMsg).get(1);
        		for(String s:lj){
        			System.out.println("PATH:" + s);
        		}
        		pokerInterfaz.getSesion().getCm().getJuego().setVisible(true);
        		pokerInterfaz.getSesion().getCm().getJuego().setCartas(lj);  		
        		/*System.out.println("Llama rmi! en update ");
        		remoteService.mostrarPrimeras3();
        		}else if(state==1){*/
        			System.out.println("Estado 2");
        			pokerInterfaz.getSesion().getCm().getJuego().cambiarCartas();
        			pokerInterfaz.getSesion().getCm().getJuego().mostrarCartasJugador();
        		//}
        
    		}else {
    			System.out.println("La lista que llega al Update esta vacia");
    		}
    	
    	
    	//Mostrar Jugadores que van a entrar a la partida
    	/*if(updateMsg!=null){
        List<String> lj=new ArrayList<String>(); 
        		lj= (List<String>) updateMsg;
        for(String j:lj){
        	System.out.println("Nombre: "+j);
        }
    		}else {
    			System.out.println("Si es Null");
    		}*/
    }
}