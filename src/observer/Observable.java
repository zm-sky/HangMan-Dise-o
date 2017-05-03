/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zippy
 */
public abstract class Observable {
    /**
     * Determina si ha cambiado algo dentro de el observable.
     */
    private boolean hasChanged;
    
    /**
     * Contiene la lista de observadores.
     */
    private final List<Observer> observers = new ArrayList();
    
    /**
     * Agrega un observer a la lista de observadores.
     * 
     * @param observer 
     */
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    
    /**
     * Remueve un observer de la lista de observadores.
     * 
     * @param observer 
     */
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    /**
     * Notifica a los observadores que hubo un cambio.
     */
    public void notifyObservers(Object o){
        for(Observer observer : observers)
            observer.update(this, o);
    }
    
    /**
     * Pone la bandera que algo ha cambiado a verdadero.
     */
    protected void setChanged(){
        hasChanged = true;
    }
    
    /**
     * Cambia la bandera para determinar que no hay cambios
     * en el observable.
     */
    protected void clearChanged(){
        hasChanged = false;
    }
    
    /**
     * Determina si el observable ha cambiado de estado.
     * 
     * @return 
     */
    public boolean hasChanged(){
        return hasChanged;
    }
}
