/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
                new FrmJuego();
            }
        });
    }
}
