/**
 * 
 */
package PruebasCliente;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import Controlador.*;
import Interfaz.DatosSesion;
/**
 * @author sala_a
 *
 */
public class Test_Cliente {

	/**
	 * Este método prueba que se realiza correctamente la conexión entre el cliente y servidor-,
	 */
	@Test
	public void prueba_probarConexion() {
		try {
			Cliente tested = new Cliente();
			tested.main(null);
			assertTrue(tested.getClient()!=null);
		} catch (RemoteException e){
			e.printStackTrace();
		}
	}
	/**
	 * Este método sirve para verificar que los avatares de los jugaodores están adecuados.
	 * @param: nUsuario es el nombre de un usuario, es necesario para llevar a cabo la prueba
	 */
	@Test
	public void prueba_mostrarAvatar()
	{
		String nUsuario = "omalagon";
		try {
			DatosSesion test = new DatosSesion(new Cliente().getRemoteService(), nUsuario, new Cliente().getClient(), null);
			assertTrue(test.mostrarAvatarSesion(nUsuario)==true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
