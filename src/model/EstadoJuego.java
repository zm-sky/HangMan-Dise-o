/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import network.Peer;
import patron.CommandManager;

/**
 *
 * @author zippy
 */
public class EstadoJuego {
    /**
     * Contiene la frase a adivinar.
     */
    private String frase;
    
    /**
     * Contiene parte del modelo que maneja conexiones.
     */
    private CommandManager manager;
    
    /**
     * Conecta el modelo a el servidor de entrada para recibir y mandar
     * datos.
     * 
     * @param IP El IP del servidor de entrada.
     * @param puerto El puerto del servidor de entrada.
     */
    public boolean conectarServidor(String IP, int puerto){
        try{
            Peer peer = new Peer();
            peer.inicializar(IP, puerto);
            peer.conectarServidor(IP, puerto);
            
            manager = new CommandManager(peer);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return false;
    }
}
