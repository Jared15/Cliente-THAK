package Interfaz;
import java.awt.*;
import javax.swing.JFrame;
 
public class PantallaCompleta{
   private GraphicsDevice gd;//tarjeta gráfica
   public PantallaCompleta(){
      //hay que usar los medios del propio sistema
      GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
   gd = env.getDefaultScreenDevice();
 }
  /**
   * establece la la ventana en pantalla completa
   * @param dm modo de despliegue 
   * @param ventana ventana a poner en pantalla completa
   */
 public void setFullScreen(DisplayMode dm, JFrame ventana){
    ventana.setUndecorated(true);
    ventana.setResizable(false);
    gd.setFullScreenWindow(ventana);
     
    //comprobamos que el sistema soporta el cambio
    if(dm != null && gd.isDisplayChangeSupported()){
       try{
      gd.setDisplayMode(dm);
    }catch(IllegalArgumentException e){}
  }
 }
 /**
  * obtiene la ventana en pantalla completa
  * @return la ventana en pantala completa
  */
 public Window getFullScreenWindow(){
    return gd.getFullScreenWindow();
 }
  
 /**
  * muestra la ventana en pantalla completa
  */
 public void restoreScreen(){
    Window w = gd.getFullScreenWindow();
    if(w != null){
       w.dispose();
  }
  gd.setFullScreenWindow(null);
 }
}