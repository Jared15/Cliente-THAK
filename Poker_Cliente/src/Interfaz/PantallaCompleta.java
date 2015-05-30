package Interfaz;
import java.awt.*;
import javax.swing.JFrame;
 
public class PantallaCompleta{
   private GraphicsDevice gd;//tarjeta gr�fica
   public PantallaCompleta(){
      //hay que usar los medios del propio sistema
      GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
   gd = env.getDefaultScreenDevice();
 }
  
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
  
 public Window getFullScreenWindow(){
    return gd.getFullScreenWindow();
 }
  
 //restauramos los valores previos
 public void restoreScreen(){
    Window w = gd.getFullScreenWindow();
    if(w != null){
       w.dispose();
  }
  gd.setFullScreenWindow(null);
 }
}