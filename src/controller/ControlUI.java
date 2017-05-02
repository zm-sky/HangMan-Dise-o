/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import command.Context;
import java.util.List;
import model.EstadoJuego;

/**
 *
 * @author zippy
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
    }
    
    /**
     * Conecta el juego a el servidor de entrada.
     * 
     * @param IP
     * @param puerto 
     */
    public void conectar(String IP, int puerto){
        
    }
    
    /**
     * Inicia el juego y muestra la pantalla al jugador.
     * 
     * @param frase La frase del juego que se estara adivinando.
     * @param conectados La lista de jugadores conectados.
     */
    @Override
    public void iniciarJuego(String frase, List<String> conectados) {
        
    }

    /**
     * Indica quien es el siguiente jugador.
     * 
     * @param siguiente 
     */
    @Override
    public void setSiguiente(String siguiente) {
        
    }

    /**
     * 
     * @param ganador 
     */
    @Override
    public void setGanador(String ganador) {
        
    }

    /**
     * Agrega una letra al tablero de letras.
     * @param letra 
     */
    @Override
    public void agregarLetra(String letra) {
        
    }
    
    public String getFrase(){
        return null;
    }
}
