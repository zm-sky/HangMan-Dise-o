/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import network.Peer;
import observer.Observable;
import patron.Command;
import patron.CommandManager;
import view.EstadoMonito;

/**
 *
 * @author zippy
 */
public class EstadoJuego extends Observable {

    /**
     * Acceso privado del constructor para que no se pueda isntanciar esta clase
     * en ninguna parte mas que aqui mismo.
     */
    private EstadoJuego() {

    }

    /**
     * Registra un turno dentro de la lista de turnos. Esto se usara para
     * determinar quien sigue.
     *
     * @param turno El turno a registrarse.
     */
    public void registrarTurno(int turno) {
        EstadoJuegoHolder.instance.registrarTurno(turno);
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
        return EstadoJuegoHolder.instance.inicializar(IP, puerto, IPServer, puertoServer);
    }

    /**
     * Manda un comando a todos los demas jugadores que esten conectados en la
     * misma red.
     *
     * @param command El comando a mandarse.
     */
    public void enviarComando(Command command) {
        EstadoJuegoHolder.instance.enviarComando(command);
    }

    /**
     * Avanza un turno hacia adelante.
     */
    public void avanzarTurno() {
        EstadoJuegoHolder.instance.avanzarTurno();
    }

    /**
     * Cambia la frase a adivinarse.
     *
     * @param frase
     */
    public void setFrase(String frase) {
        EstadoJuegoHolder.instance.setFrase(frase);
    }

    /**
     * Regresa lo que se lleva adivinado de la frase.
     *
     * @return La frase que se lleva adivinada.
     */
    public String getFraseActual() {
        return EstadoJuegoHolder.instance.getFraseActual();
    }

    /**
     * Cambia lo que se lleva adivinado de la frase.
     *
     * @param fraseActual
     */
    public void setFraseActual(String fraseActual) {
        EstadoJuegoHolder.instance.setFraseActual(fraseActual);
    }

    /**
     * Regresa la frase a adivinarse.
     *
     * @return
     */
    public String getFrase() {
        return EstadoJuegoHolder.instance.getFrase();
    }

    /**
     * Regresa el turno actual.
     * 
     * @return 
     */
    public int getTurnoActual(){
        return EstadoJuegoHolder.instance.getTurnoActual();
    }
    
    /**
     * Regresa el turno que se nos asigno.
     * @return 
     */
    public int getTurno(){
        return EstadoJuegoHolder.instance.getTurno();
    }
    
    /**
     * Regresa el indice del turno.
     * @return 
     */
    public int getTurnoIndex(){
        return EstadoJuegoHolder.instance.getTurnoIndex();
    }
    
    /**
     * Determina si el turno ya esta registrado.
     * 
     * @return 
     */
    public boolean estaTurnoRegistrado(int turno){
        return EstadoJuegoHolder.instance.estaTurnoRegistrado(turno);
    }
    
        /**
     * Agrega una letra a la lista de letras que se han intentado adivinar.
     *
     * @param letra
     */
    public void agregarLetra(Character letra) {
        EstadoJuegoHolder.instance.agregarLetra(letra);
    }

    /**
     * Verifica si la letra indicada es la correcta.
     * 
     * @return 
     */
    public boolean verificarLetra(Character letra){
        return EstadoJuegoHolder.instance.verificarLetra(letra);
    }
    
    /**
     * Indica si la letra ya esta escrita.
     * 
     * @param letra
     * @return 
     */
    public boolean letraYaEscrita(Character letra){
        return EstadoJuegoHolder.instance.letraYaEscrita(letra);
    }
    
    /**
     * Contiene toda la informacion relacionada a el juego y lo que va navegando
     * atraves de la red.
     *
     * El modelo del juego.
     */
    private static class EstadoJuegoHolder extends EstadoJuego{

        /**
         * El numero de errores que se han cometido.
         */
        private int errores = 0;
        
        /**
         * Contiene el turno que nos toco.
         */
        private int turno;

        /**
         * Contiene el turno actual del juego.
         */
        private int turnoActual = 0;

        /**
         * Contiene el indice dentro de la lista para determinar
         * el turno actual. La lista tiene todos los turnos registrados
         * en orden de menor a mayor.
         */
        private int turnoIndex = -1;
        
        /**
         * Indica si el juego ha sido ganado.
         * Esto solo se usa para estar contando el tiempo
         * mientras no se haya ganado.
         */
        private boolean juegoGanado;
        
        /**
         * Contiene la lista de turnos asignados a todos los jugadores.
         */
        private List<Integer> turnosRegistrados;

        /**
         * Contiene la lista de letras que se han tratado
         * de adivinar.
         */
        private List<Character> letrasAñadidas;
        
        /**
         * Contiene la frase a adivinar.
         */
        private String frase;

        /**
         * Contiene lo que se ha adivinado de la frase que se tiene que
         * adivinar.
         */
        private String fraseActual = "";

        /**
         * Contiene el contador para adivinar.
         * Cuando quede en 0, ya no puede adivinar
         * el jugador.
         */
        private TimeWatch watch;
        
        /**
         * Contiene el estado actual del monito.
         */
        private EstadoMonito estadoMonito;

        /**
         * Contiene parte del modelo que maneja conexiones.
         */
        private CommandManager manager;

        /**
         * La instancia de este singleton.
         */
        private static final EstadoJuegoHolder instance = new EstadoJuegoHolder();

        /**
         * Constructor privado.
         */
        private EstadoJuegoHolder(){
            turnosRegistrados = new ArrayList();
            letrasAñadidas = new ArrayList();
            
            watch = new TimeWatch();
        }
        
        /**
         * Inicializa el peer en la direccion dada por los parametros. Tambien
         * se conecta hacia el servidor destinatario.
         *
         * @param IP La IP de este peer.
         * @param puerto El puerto de este peer.
         * @return
         */
        @Override
        public boolean inicializar(String IP, int puerto, String IPServer, int puertoServer) {
            try {
                Peer peer = new Peer();
                peer.inicializar(IP, puerto);
                turno = peer.conectarServidor(IPServer, puertoServer);
                turnosRegistrados.add(turno);
                
                manager = new CommandManager(peer);
                return true;
            } catch (Exception e) {
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
        @Override
        public void enviarComando(Command command) {
            try {
                manager.mandarComando(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Avanza un turno hacia adelante.
         *
         * @return
         */
        @Override
        public void avanzarTurno() {
            /**
             * Antes de seguir, tenemos que checar si el mono esta muerto o no.
             */
            if(estadoMonito != EstadoMonito.MUERTO){
                turnoIndex = turnoIndex == turnosRegistrados.size() - 1 ? 0 : turnoIndex + 1;
                System.out.println("Turno Index: " + turnoIndex);

                /**
                 * Obtenemos el turno actual..
                 */
                turnoActual = turnosRegistrados.get(turnoIndex);

                /**
                 * Esto simplemente sirve para decirle a la interfaz que texto poner en
                 * la parte de "Le toca a: JUGADOR". No se hace nada con esta variable.
                 */
                String nombreJugador = turno == turnoActual ? "(Te toca a ti adivinar!)" : "Jugador " + (turnoIndex+1);

                /**
                 * Mandamos a los observadores un boolean
                 * si es nuestro turno o no.
                 */
                notifyObservers(turno == turnoActual);
                notifyObservers("cambio turno:" + nombreJugador);

                /**
                 * Reiniciamos el contador a 0, ya que un nuevo turno empezo.
                 */
                watch.reset();

                /**
                 * Vamos a verificar si el contador ya ha sido activado.
                 * Suele estar desactivado solamente cuando apenas
                 * se inicia el juego. En este caso, tenemos que hacer un
                 * nuevo hilo donde el contador se va a estar actualizando,
                 * y la interfaz grafica vaya mostrando el tiempo restante.
                 */
                if(!watch.isStarted()){
                    watch.start();

                    new Thread(new Runnable(){
                        public void run(){
                            try{
                                /**
                                 * Mientras el juego no ha sido ganado,
                                 * vamos a estar contando el tiempo.
                                 */
                                while(!juegoGanado){
                                    //30 segundos - Tiempo que ha pasado.
                                    int segundosRestantes = (int) (30 - watch.time(TimeUnit.SECONDS));

                                    //Esto se hace ya que si hay lag, el contador no se
                                    //va a reiniciar hasta que reciba el comando de siguiente
                                    //turno del jugador.
                                    if(segundosRestantes < 0)
                                        segundosRestantes = 0;

                                    //Notificamos a el observer que avanzamos un segundo.
                                    notifyObservers(segundosRestantes);

                                    //Dormimos 100 millisegundos para volver a checar el tiempo
                                    //y que no se sature de instrucciones el processador.
                                    Thread.sleep(100);
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
            else
                notifyObservers("Mono Muerto");
        }

        /**
         * Verifica si la letra es la correcta.
         * 
         * @param letra
         * @return 
         */
        public boolean verificarLetra(Character letra){
            int currentIndex = fraseActual.length()-1;

            //Esto es en caso de que sea la primera letra que
            //se trate de agregar.
            if(currentIndex == -1)
                currentIndex++;
            
            /**
             * Esto servira para determinar si la letra existe en
             * la palabra que se indico.
             */
            boolean letraEncontrada = false;
            
            
            for(int i = 0; i < frase.length(); i++){
                if(frase.charAt(i) == letra){
                    letraEncontrada = true;
                    
                    StringBuilder builder = new StringBuilder(fraseActual);
                    builder.setCharAt(i, frase.charAt(i));
                    fraseActual = builder.toString();
                }
            }
            
            /**
             * Esta variable servira para ver cuantas letras
             * faltan.
             */
            int letrasFaltantes = fraseActual.length() - fraseActual.replace("_", "").length();
            
            if(!letraEncontrada){
                errores++;
                estadoMonito = EstadoMonito.getEstadoPorNumeroErrores(errores);
                
                notifyObservers(estadoMonito);
            }
            else
                watch.reset();
            
            notifyObservers("FraseActual:" + fraseActual);
            
            if(!letraEncontrada)
                notifyObservers("LetraAñadida:" + letra);
            
            if(letrasFaltantes == 0)
                notifyObservers("Gane");
            
            return letraEncontrada;
        }
        
        /**
         * Regresa si el turno ya esta registrado.
         * 
         * @return 
         */
        public boolean estaTurnoRegistrado(int turno){
            for(Integer i : turnosRegistrados)
                if(i == turno)
                    return true;
            
            return false;
        }
        
        /**
         * Agrega una letra a la lista de letras que se
         * han intentado adivinar.
         * 
         * @param letra 
         */
        public void agregarLetra(Character letra){
            letrasAñadidas.add(letra);
        }
        
        /**
         * Registra un turno dentro de la lista de turnos registrados.
         */
        @Override
        public synchronized void registrarTurno(int turno) {
            turnosRegistrados.add(turno);
            Collections.sort(turnosRegistrados);
            System.out.println("El turno ha sido registrado.");
            
            for(Integer i : turnosRegistrados){
                System.out.println(i);
            }
            
            notifyObservers("conectado");
        }
        
        /**
         * Setea la frase.
         *
         * @param frase
         */
        @Override
        public void setFrase(String frase) {
            this.frase = frase;
            
            for(int i = 0; i < frase.length(); i++){
                if(frase.charAt(i) == ' ')
                    fraseActual+= " ";
                
                fraseActual+= "_";
            }
            
            notifyObservers("iniciado:" + fraseActual);
        }

        /**
         * Determina si la letra ya esta escrita.
         * 
         * @param letra
         * @return 
         */
        public boolean letraYaEscrita(Character letra){
            for(Character c : letrasAñadidas)
                if(c == letra)
                    return true;
            
            return false;
        }
        
        /**
         * Regresa lo que se lleva adivinado de la frase.
         *
         * @return La frase que se lleva adivinada.
         */
        @Override
        public String getFraseActual() {
            return fraseActual;
        }

        /**
         * Cambia lo que se lleva adivinado de la frase.
         *
         * @param fraseActual
         */
        @Override
        public void setFraseActual(String fraseActual) {
            this.fraseActual = fraseActual;
        }
        
        /**
         * Regresa la frase que se esta descifrando.
         *
         * @return
         */
        @Override
        public String getFrase() {
            return frase;
        }

        /**
         * Regresa el turno actual.
         * 
         * @return 
         */
        @Override
        public int getTurnoActual(){
            return turnoActual;
        }
        
        /**
         * Regresa el indice del turno.
         * 
         * @return 
         */
        public int getTurnoIndex(){
            return turnoIndex;
        }
        
        /**
         * Regresa el turno que se nos asigno.
         *
         * @return
         */
        @Override
        public int getTurno() {
            return turno;
        }
    }
}
