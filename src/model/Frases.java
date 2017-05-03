/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author zippy
 */
public class Frases {
    
    private static final List<String> listaPalabras = new ArrayList();
    
    /**
     * Inicializa todas las frases.
     */
    static{
        try{
            InputStream in = Frases.class.getResourceAsStream("/recursos/Palabras.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
            String palabra;
            
            while((palabra = reader.readLine()) != null)
                listaPalabras.add(palabra);
            
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Regresa una frase establecida.
     * @return 
     */
    public static String generarFrase(){
        return listaPalabras.get(new Random().nextInt(listaPalabras.size()));
    }
}
