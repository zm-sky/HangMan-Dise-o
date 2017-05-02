/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import network.Peer;
import patron.Command;
import patron.CommandManager;

/**
 *
 * @author zippy
 */
public class EstadoJuego {
    /**
     * Acceso privado del constructor para que no se
     * pueda isntanciar esta clase en ninguna parte mas que
     * aqui mismo.
     */
    private EstadoJuego(){}
    
    /**
     * Regresa la isntancia del estado de juego.
     * 
     * @return La instancia del estado de juego.
     */
    public static EstadoJuego getInstance(){
        return EstadoJuegoHolder.instance;
    }
    
    /**
     * Conecta el CommandManager al servidor para obtener los datos
     * que se ocupen. Si no se pudo realizar la conexion al servidor,
     * este metodo regresara falso. De lo contrario, se regresara
     * verdadero y estara hecha la conexion.
     * 
     * @param IP La IP del servidor.
     * @param puerto El puerto del servidor.
     * @return 
     */
    public boolean conectarServidor(String IP, int puerto){
        return EstadoJuegoHolder.conectarServidor(IP, puerto);
    }
    
    /**
    * Manda un comando a todos los demas jugadores que esten 
    * conectados en la misma red.
    * 
    * @param command El comando a mandarse.
    */
    public static void enviarComando(Command command) {
        EstadoJuegoHolder.enviarComando(command);
    }
    
    /**
     * Contiene toda la informacion relacionada a el
     * juego y lo que va navegando atraves de la red.
     * 
     * El modelo del juego.
     */
    private static class EstadoJuegoHolder{
        /**
        * Contiene la frase a adivinar.
        */
       private static String frase;

       /**
        * Contiene parte del modelo que maneja conexiones.
        */
       private static CommandManager manager;

       /**
        * La instancia de este singleton.
        */
       private static final EstadoJuego instance = new EstadoJuego();
       
       /**
        * Conecta el modelo a el servidor de entrada para recibir y mandar
        * datos.
        * 
        * @param IP El IP del servidor de entrada.
        * @param puerto El puerto del servidor de entrada.
        */
        public static boolean conectarServidor(String IP, int puerto){
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
        
        /**
         * Manda un comando a todos los demas jugadores que esten 
         * conectados en la misma red.
         * 
         * @param command El comando a mandarse.
         */
        public static void enviarComando(Command command) {
            try{
                manager.mandarComando(command);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
