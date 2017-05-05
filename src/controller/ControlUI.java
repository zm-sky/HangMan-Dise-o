/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.EstadoJuego;

/**
 *
 * @author Roberto Pedraza Coello
 */
public class ControlUI{

    /**
     * Inicia el juego.
     */
    public void iniciarJuego(){
        try{
            EstadoJuego.getInstance().iniciarJuegoCliente();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Verifica si la letra que se tecleo es
     * correcta en cuestion con la palabra.
     */
    public void verificarLetra(Character letra){
        EstadoJuego.getInstance().verificarLetra(letra);
    }
    
    /**
     * Determina si se pudo conectar el modelo al
     * servidor de entrada.
     * 
     * @param IP
     * @param puerto 
     */
    public boolean conectar(String IP, int puerto){
        return EstadoJuego.getInstance().inicializar(IP, puerto, "localhost", 6000);
    }

}
