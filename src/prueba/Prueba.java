/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.EstadoJuego;
import view.FrmJuego;

/**
 *
 * @author zippy
 */
public class Prueba {
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                EstadoJuego juego = EstadoJuego.getInstance();
                
                if(juego.inicializar("localhost", 4999, "localhost", 6000))
                    new FrmJuego().setVisible(true);
                else{
                    JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor.");
                    System.exit(0);
                }
            }
        });
    }
}
