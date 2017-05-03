/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import command.Context;
import commands.C_IniciarJuego;
import commands.C_MovimientoCorrecto;
import commands.C_MovimientoIncorrecto;
import commands.C_Pierde;
import commands.C_RegistrarTurno;
import commands.C_SiguienteTurno;
import javax.swing.JOptionPane;
import model.EstadoJuego;
import model.Frases;
import patron.Command;

/**
 *
 * @author Roberto Pedraza Coello
 */
public class ControlUI extends Context{

    /**
     * Contiene el estado actual del juego.
     * 
     */
    private EstadoJuego estadoJuego;
    
    /**
     * Crea una isntancia de control encargada de manejar
     * eventos del juego.
     */
    public ControlUI(){
        setContext(this);
        estadoJuego = EstadoJuego.getInstance();
    }
    
    /**
     * Inicia el juego dentro del cliente.
     * Esto se hace aqui para mandarle el comando a todos
     * los demas jugadores que ya se inicio el juego.
     * 
     * Si mandaramos el comando Iniciar juego dentro del
     * otro metodo, el comando se seguiria llamando indefinidamente.
     */
    public void iniciarJuegoCliente(){
        String frase = Frases.generarFrase();
        
        System.out.println("Frase escojida: " + frase);
        Command comando = new C_IniciarJuego(frase);
        Command siguiente = new C_SiguienteTurno();
        
        estadoJuego.setFrase(frase);
        estadoJuego.enviarComando(comando);
        estadoJuego.enviarComando(siguiente);
        estadoJuego.avanzarTurno();
    }
    
    /**
     * Verifica si la letra que se tecleo es
     * correcta en cuestion con la palabra.
     */
    public void verificarLetra(Character letra){
        Command comando;
        System.out.println("Verificando letra: " + letra);
        
        /**
         * Primero vamos a verificar que no hayamos ya 
         * escrito la letra. En dado caso que si, no hacemos
         * nada hasta que se ecoja otra.
         */
        if(!estadoJuego.letraYaEscrita(letra)){
            /**
             * Si la letra es la correcta..
             */
            if(estadoJuego.verificarLetra(letra)){
                estadoJuego.agregarLetra(letra);
                comando = new C_MovimientoCorrecto(letra);
                System.out.println("Movimiento correcto.");
            }
            /**
             * Si no es correcta, tenemos que pasar al siguiente turno.
             */
            else{
                comando = new C_MovimientoIncorrecto(letra);
                estadoJuego.avanzarTurno();

                System.out.println("Movimiento Incorrecto.");
            }

            estadoJuego.enviarComando(comando);
        }
    }
    
    /**
     * Indica que el juego se ha perdido.
     */
    public void perderJuego(){
        Command perder = new C_Pierde(null);
        estadoJuego.enviarComando(perder);
        
        JOptionPane.showMessageDialog(null, "El juego ha sido perdido!\nLa palabra era: " + estadoJuego.getFrase());
        System.exit(0);
    }
    
    /**
     * Gana el juego.
     */
    public void ganarJuego(){
        Command perder = new C_Pierde("Jugador " + (estadoJuego.getTurnoIndex() + 1));
        estadoJuego.enviarComando(perder);
        
        JOptionPane.showMessageDialog(null, "Usted ha ganado el juego, felicidades!");
        System.exit(0);
    }
    
    /**
     * Agrega una letra al tablero de letras.
     * 
     * @param letra La letra a agregarse.
     */
    @Override
    public void agregarLetra(Character letra) {
        estadoJuego.agregarLetra(letra);
        estadoJuego.verificarLetra(letra);
    }
    
    /**
     * Conecta el juego a el servidor de entrada.
     * 
     * @param IP
     * @param puerto 
     */
    public boolean conectar(String IP, int puerto){
        /**
         * Si nos pudimos conectar al servidor, entonces tenemos que mandarle
         * a los que esten conectados nuestro turno.
         */
        if(estadoJuego.inicializar("localhost", puerto, "localhost", 6000)){
            Command registrarTurno = new C_RegistrarTurno(estadoJuego.getTurno());
            estadoJuego.enviarComando(registrarTurno);
            
            return true;
        }
        
        /**
         * Si no pudimos conectarnos al server mandamos false.
         */
        return false;
    }
    
    /**
     * Inicia el juego y muestra la pantalla al jugador.
     * 
     * @param frase La frase del juego que se estara adivinando.
     */
    @Override
    public void iniciarJuego(String frase) {
        estadoJuego.setFrase(frase);
    }

    /**
     * Indica que se tiene que avanzar el turno.
     */
    @Override
    public void avanzarTurno() {
        System.out.println("Avanzando turno.");
        estadoJuego.avanzarTurno();
    }

    /**
     * 
     * @param ganador 
     */
    @Override
    public void setGanador(String ganador) {
        /**
         * Verificamos si hay un ganador enrealidad, o 
         * si el juego se perdio.
         */
        if(ganador == null){
            JOptionPane.showMessageDialog(null, "El juego ha sido perdido!\nLa palabra era: " + estadoJuego.getFrase());
            System.exit(0);
        }
        else{
            JOptionPane.showMessageDialog(null, "Usted ha perdido.\nEl ganador ha sido el Jugador " + (estadoJuego.getTurnoIndex()+1) + "!");
            System.exit(0);
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getFrase(){
        return estadoJuego.getFrase();
    }

    /**
     * Registra el turno de un jugador dentro
     * de la lista de turnos.
     * 
     * @param turno 
     */
    @Override
    public void registrarTurno(int turno) {
        /**
         * Determinamos si el turno que recibimos esta registrado.
         * En caso de que no, tenemos que registrarlo, y mandarle a
         * la fuente que tambien nos registre a nosotros.
         */
        if(!estadoJuego.estaTurnoRegistrado(turno)){
            estadoJuego.registrarTurno(turno);
            Command registrarTurno = new C_RegistrarTurno(estadoJuego.getTurno());
            estadoJuego.enviarComando(registrarTurno);
        }
    }
}
