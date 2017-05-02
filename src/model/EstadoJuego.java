/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import network.Peer;
import patron.Command;
import patron.CommandManager;
import view.EstadoMonito;

/**
 *
 * @author zippy
 */
public class EstadoJuego {

    /**
     * Acceso privado del constructor para que no se pueda isntanciar esta clase
     * en ninguna parte mas que aqui mismo.
     */
    private EstadoJuego() {
    }

    /**
     * Regresa la isntancia del estado de juego.
     *
     * @return La instancia del estado de juego.
     */
    public static EstadoJuego getInstance() {
        return EstadoJuegoHolder.instance;
    }

    /**
     * Inicializa el peer en la direccion dada por los parametros. Tambien se
     * conecta hacia el servidor destinatario.
     *
     * @param IP La IP de este peer.
     * @param puerto El puerto de este peer.
     * @return
     */
    public boolean inicializar(String IP, int puerto, String IPServer, int puertoServer) {
        return EstadoJuegoHolder.inicializar(IP, puerto, IPServer, puertoServer);
    }

    /**
     * Manda un comando a todos los demas jugadores que esten conectados en la
     * misma red.
     *
     * @param command El comando a mandarse.
     */
    public void enviarComando(Command command) {
        EstadoJuegoHolder.enviarComando(command);
    }

    /**
     * Contiene toda la informacion relacionada a el juego y lo que va navegando
     * atraves de la red.
     *
     * El modelo del juego.
     */
    private static class EstadoJuegoHolder {

        /**
         * Contiene el turno que nos toco.
         */
        private static int turno;
        /**
         * Contiene la frase a adivinar.
         */
        private static String frase;

        /**
         * Contiene el estado actual del monito.
         */
        private static EstadoMonito estadoMonito;

        /**
         * Contiene parte del modelo que maneja conexiones.
         */
        private static CommandManager manager;

        /**
         * La instancia de este singleton.
         */
        private static final EstadoJuego instance = new EstadoJuego();

        /**
         * Inicializa el peer en la direccion dada por los parametros. Tambien
         * se conecta hacia el servidor destinatario.
         *
         * @param IP La IP de este peer.
         * @param puerto El puerto de este peer.
         * @return
         */
        public static boolean inicializar(String IP, int puerto, String IPServer, int puertoServer) {
            try{
                Peer peer = new Peer();
                peer.inicializar(IP, puerto);
                turno = peer.conectarServidor(IPServer, puertoServer);
                
                manager = new CommandManager(peer);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
            
            return false;
        }

        /**
         * Manda un comando a todos los demas jugadores que esten conectados en
         * la misma red.
         *
         * @param command El comando a mandarse.
         */
        public static void enviarComando(Command command) {
            try {
                manager.mandarComando(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
