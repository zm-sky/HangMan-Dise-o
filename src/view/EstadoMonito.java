/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author zippy
 */
public enum EstadoMonito {
    VACIO(0),
    CUERDA(1),
    CABEZA(2),
    TORSO(3),
    CUERPO(4),
    BRAZOS_PIERNAS(5),
    ZAPATOS(6),
    MUERTO(7);
    
    /**
     * Basicamente es igual a la cantidad de errores que 
     * se han cometido.
     */
    int id;
    
    /**
     * La imagen del monito dependiendo de cada estado.
     */
    BufferedImage imagen;
    
    /**
     * Esto se llama cuando se usa un estado por primera vez.
     * Inicializa las imagenes adecuadamente y en su orden debido.
     */
    static{
        try{
            BufferedImage imagenMono = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("recursos/man.png"));
            int offsetX = 0;

            for(EstadoMonito estado : values()){
                estado.imagen = imagenMono.getSubimage(offsetX, 0, 75, 200);
                
                offsetX+=75;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Inicializa el ID del estado.
     * @param id El id del estado.
     */
    EstadoMonito(int id){
        this.id = id;
    }
    
    /**
     * Regresa el estado correcto del monito a partir
     * de los errores que se han cometido.
     * 
     * @param errores 
     * @return EstadoMonito el estado del mono a partir de los errores cometidos.
     */
    public static EstadoMonito getEstadoPorNumeroErrores(int errores){
        for(EstadoMonito estado : values())
            if(estado.id == errores)
                return estado;
        
        return null;
    }
    
    /**
     * Regresa la imagen del monito.
     * 
     * @return 
     */
    public BufferedImage getImagen(){
        return imagen;
    }
}
